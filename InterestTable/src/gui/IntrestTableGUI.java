package gui;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;
import model.Interest;

public class IntrestTableGUI extends Application {
	private TextArea displayArea;
	private TextField principal, rate;

	@Override
	public void start(Stage calculator) {
		int sceneWidth = 700, sceneHeight = 400;

		displayArea = new TextArea();
		displayArea.setPrefSize(540, 200);
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(displayArea);

		Label principalLabel = new Label("Principal:");
		principal = new TextField();
		principal.setPrefWidth(225);

		Label rateLabel = new Label("Rate(Percentage):");
		rate = new TextField();
		rate.setPrefWidth(135);

		Label sliderLabel = new Label("Number of Years:");
		sliderLabel.setPrefWidth(100);
		Slider horizontalSlider = new Slider();
		horizontalSlider.setMin(1);
		horizontalSlider.setMax(25);
		horizontalSlider.setValue(1);
		horizontalSlider.setMajorTickUnit(4);
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);
		horizontalSlider.setPrefWidth(425);
		horizontalSlider.valueProperty().addListener(e -> Interest.setYears((int) horizontalSlider.getValue()));

		Button simpleIntrest = new Button("SimpleIntrest");
		simpleIntrest.setPrefSize(150, 20);
		simpleIntrest.setOnAction(e -> {
			displayArea.clear();
			displayArea.appendText("Principal: " + Double.parseDouble(principal.getText()) + ", Rate: "
					+ Double.parseDouble(rate.getText()) + "\n");
			displayArea.appendText("Year, Simple Interest Amount" + "\n");
			displayArea.appendText(Interest.calcSimpleInterest(Double.parseDouble(principal.getText()),
					Double.parseDouble(rate.getText())));
		});

		Button compoundIntrest = new Button("CompoundIntrest");
		compoundIntrest.setPrefSize(160, 20);
		compoundIntrest.setOnAction(new CompoundHandler());

		Button bothIntrests = new Button("BothIntrests");
		bothIntrests.setPrefSize(150, 20);
		bothIntrests.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				displayArea.clear();
				displayArea.appendText("Principal: " + Double.parseDouble(principal.getText()) + ", Rate: "
						+ Double.parseDouble(rate.getText()) + "\n");
				displayArea.appendText("Year, Simple Interest Amount, Compound Interest Amount" + "\n");
				displayArea.appendText(Interest.calcBothInterests(Double.parseDouble(principal.getText()),
						Double.parseDouble(rate.getText())));
			}
		});

		ButtonBar buttonBar = new ButtonBar();
		ButtonBar.setButtonData(simpleIntrest, ButtonData.APPLY);
		ButtonBar.setButtonData(compoundIntrest, ButtonData.APPLY);
		ButtonBar.setButtonData(bothIntrests, ButtonData.APPLY);
		buttonBar.getButtons().addAll(simpleIntrest, compoundIntrest, bothIntrests);

		FlowPane pane = new FlowPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(30, 80, 20, 80));
		pane.getChildren().addAll(scrollPane, principalLabel, principal, rateLabel, rate, sliderLabel, horizontalSlider,
				buttonBar);

		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		calculator.setTitle("Intrest Table Calculator");
		calculator.setScene(scene);
		calculator.show();
	}

	private class CompoundHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			displayArea.clear();
			displayArea.appendText("Principal: " + Double.parseDouble(principal.getText()) + ", Rate: "
					+ Double.parseDouble(rate.getText()) + "\n");
			displayArea.appendText("Year, Compound Interest Amount" + "\n");
			displayArea.appendText(Interest.calcCompoundInterest(Double.parseDouble(principal.getText()),
					Double.parseDouble(rate.getText())));
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
