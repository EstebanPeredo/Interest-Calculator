package model;

import java.text.NumberFormat;

public class Interest {
	private static int years = 1;

	public static void setYears(int years) {
		Interest.years = years;
	}

	public static String calcSimpleInterest(double principal, double rate) {
		String simpleInterest = "";
		for (int yearIndex = 1; yearIndex <= Interest.years; yearIndex++) {
			simpleInterest += yearIndex + "-->"
					+ NumberFormat.getCurrencyInstance().format(principal + (principal * (rate / 100) * yearIndex))
					+ "\n";
		}
		simpleInterest.trim();
		return simpleInterest;
	}

	public static String calcCompoundInterest(double principal, double rate) {
		String compoundInterest = "";
		for (int yearIndex = 1; yearIndex <= Interest.years; yearIndex++) {
			compoundInterest += yearIndex + "-->"
					+ NumberFormat.getCurrencyInstance().format(principal * Math.pow(1 + rate / 100, yearIndex)) + "\n";
		}
		compoundInterest.trim();
		return compoundInterest;
	}

	public static String calcBothInterests(double principal, double rate) {
		String bothInterests = "";
		for (int yearIndex = 1; yearIndex <= Interest.years; yearIndex++) {
			bothInterests += yearIndex + "-->"
					+ NumberFormat.getCurrencyInstance().format(principal + (principal * (rate / 100) * yearIndex))
					+ "-->" + NumberFormat.getCurrencyInstance().format(principal * Math.pow(1 + rate / 100, yearIndex))
					+ "\n";
		}
		bothInterests.trim();
		return bothInterests;
	}
}
