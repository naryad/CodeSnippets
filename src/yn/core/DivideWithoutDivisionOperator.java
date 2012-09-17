package yn.core;

public class DivideWithoutDivisionOperator {
	public static int divide(int dividend, int divisor) {
		if (divisor == 0)
			throw new ArithmeticException("/ by zero");
		
		if (divisor > dividend)
			return 0;
		
		if (divisor == dividend) 
			return 1;
		
		int quotient = 0;
		while (dividend > divisor) {
			++quotient;
			dividend -= divisor;
		}
		System.out.println("Remainder = " + (dividend));
		return quotient;
	}
	
	public static void main(String[] args) {
		System.out.println(divide(965, 51));
	}
}
