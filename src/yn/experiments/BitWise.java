package yn.experiments;

public class BitWise {
	public static void main(String[] args) {

		byte i = 1, j = -1;
		// left operand is bit pattern, right operand is number of shift
		// positions
		System.out.println(i << 1); // left shift i by 1 position = 2
		System.out.println(i >> 1); // right shift i by 1 position = 0

		System.out.println(j >> 1); // signed right shift j by 1 position = -1
		System.out.println((byte) (j >>> 1)); // unsigned -1

		System.out.println("3 << 1  = " + (3 << 1)); // 6

		byte a = -128;
		System.out.println("First bit stores the sign a = " + a + ", ~a = "
				+ ~a);

		int bitmask = 0x000F;
		int val = 0x2222;
		//System.out.println(Integer.toBinaryString(bitmask));
		//System.out.println(Integer.toBinaryString(val));
		System.out.println(val & bitmask); // prints "2"
		System.out.println(val | bitmask); // prints "8751"
		System.out.println(val ^ bitmask); // prints "8749"

	}
}
