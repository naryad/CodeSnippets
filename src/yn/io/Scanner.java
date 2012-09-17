package yn.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			InstantiationException {
		Main.class.getMethod(new Scanner(System.in).next(), null).invoke(null, null);
		
		System.out.println("---------------Start of public methods");
		for (Method m : Main.class.getMethods()) {
			System.out.println(m.toString());
		}
		System.out.println("---------------End of public methods");
		
		System.out.println("---------------Start of declared methods");
		for (Method m : Main.class.getDeclaredMethods()) {
			System.out.println(m.toString());
		}
		System.out.println("---------------End of declared methods");
	}

	private static void scanner7() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Give me a bunch of numbers in a line (or 'exit')");
		while (!sc.hasNext("exit")) {
			Scanner lineSc = new Scanner(sc.nextLine());
			int sum = 0;
			while (lineSc.hasNextInt()) {
				sum += lineSc.nextInt();
			}
			System.out.println("Sum is " + sum);
		}
	}

	private static void scanner6() {
		Scanner a = new Scanner(System.in);
		int x = a.nextInt();
		int y = a.nextInt();
		System.out.println("x = " + x + ", y = " + y);
	}

	private static void scanner5() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			double number = Double.parseDouble(line);
			System.out.println("Number is: " + number);
		}
	}

	private static void scanner4() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String s = br.readLine();
			double number = Double.parseDouble(s);
			System.out.println("Number is:" + number);
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	private static void scanner3() {
		Scanner sc = new Scanner(System.in);
		int number;
		do {
			System.out.println("Please enter a positive number!");
			while (!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next(); // this is important!
			}
			number = sc.nextInt();
		} while (number <= 0);
		System.out.println("Thank you! Got " + number);
	}

	private static void scanner2() {
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNext("exit")) {
			System.out.println(sc.hasNextInt() ? "(int) " + sc.nextInt() : sc
					.hasNextLong() ? "(long) " + sc.nextLong() : sc
					.hasNextDouble() ? "(double) " + sc.nextDouble() : sc
					.hasNextBoolean() ? "(boolean) " + sc.nextBoolean()
					: "(String) " + sc.next());
		}
	}

	private static void scanner1() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a vowel, lowercase!");
		while (!sc.hasNext("[aeiou]")) {
			System.out.println("That's not a vowel!");
			sc.next();
		}
		String vowel = sc.next();
		System.out.println("Thank you! Got " + vowel);
	}
}