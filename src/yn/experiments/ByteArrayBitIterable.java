package yn.experiments;

import java.util.Iterator;

public class ByteArrayBitIterable implements Iterable<Boolean> {
	private final byte[] array;

	public ByteArrayBitIterable(byte[] array) {
		this.array = array;
	}

	public Iterator<Boolean> iterator() {
		return new Iterator<Boolean>() {
			private int bitIndex = 0;
			private int arrayIndex = 0;

			public boolean hasNext() {
				return (arrayIndex < array.length) && (bitIndex < 8);
			}

			public Boolean next() {
				Boolean val = (array[arrayIndex] >> (7 - bitIndex) & 1) == 1;
				bitIndex++;
				if (bitIndex == 8) {
					bitIndex = 0;
					arrayIndex++;
				}
				return val;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] a) {
		ByteArrayBitIterable test = new ByteArrayBitIterable(new byte[] {
				(byte) 0xAA, (byte) 0xAA });
		for (boolean b : test)
			System.out.println(b);
	}
}