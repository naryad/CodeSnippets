package yn.time;

public class Timing {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("Dummy statement");
		System.out.println(System.currentTimeMillis() - start);
	}
}
