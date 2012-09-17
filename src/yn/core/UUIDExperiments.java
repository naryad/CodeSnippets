package yn.core;

import java.util.UUID;

public class UUIDExperiments {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID().getLeastSignificantBits());
		/*while (true) {
			UUID x = UUID.randomUUID();
			UUID y = UUID.randomUUID();
			if (x.equals(y)) break;
		}
		System.out.println("It took " + (System.currentTimeMillis() - start)/1000 + " seconds for world to end.");*/
	}
}
