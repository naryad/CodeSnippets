package yn.primitivedatatypes;

public class DoubleExperiments {
	public static void main(String[] args) {
		double rate = 14.99;
		double percentage = 10;
		double roundedCost = (rate * percentage) / 100; // round off to the
														// nearest value.
		double finalRate = rate + myRound(roundedCost, 2);

		if (finalRate == 16.49)
			System.out.println("Its proper");
		else {
			System.out.println(finalRate);
			System.out.println("Wrong");
		}
			
	}

	public static double myRound(double value, int roundRange) {
		double hundredMultiple = (float) Math.pow(10, roundRange);
		int rangeValue = (int) (value * hundredMultiple);
		int tempValue = rangeValue % 10;
		if (tempValue < 5)
			tempValue = 5 - tempValue;
		else
			tempValue = 10 - tempValue;
		rangeValue = rangeValue + tempValue;

		return rangeValue / hundredMultiple;

	}

}
