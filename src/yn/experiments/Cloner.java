package yn.experiments;

class CoffeeCup implements Cloneable {

	private Coffee innerCoffee = new Coffee(0);

	public Object clone() {
		CoffeeCup copyCup = null;
		try {
			copyCup = (CoffeeCup) super.clone();
		} catch (CloneNotSupportedException e) {
			// this should never happen
			throw new InternalError(e.toString());
		}
		copyCup.innerCoffee = (Coffee) innerCoffee.clone();
		return copyCup;
	}

	public void add(int amount) {
		innerCoffee.add(amount);
	}

	public int releaseOneSip(int sipSize) {
		return innerCoffee.remove(sipSize);
	}

	public int spillEntireContents() {
		return innerCoffee.removeAll();
	}
}

class Coffee implements Cloneable {

	private int volume; // Volume in milliliters

	Coffee(int volume) {
		this.volume = volume;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen
			throw new InternalError(e.toString());
		}
	}

	public void add(int amount) {
		volume += amount;
	}

	public int remove(int amount) {
		int v = amount;
		if (volume < amount) {
			v = volume;
		}
		volume -= v;
		return v;
	}

	public int removeAll() {
		int all = volume;
		volume = 0;
		return all;
	}
}

public class Cloner {
	public static void main(String[] args) {
		CoffeeCup original = new CoffeeCup();
		original.add(75); // original now contains 75 ml of coffee
		CoffeeCup copy = (CoffeeCup) original.clone();
		copy.releaseOneSip(25);
		// Copy now contains 50 ml of coffee.
		// Original still has 75 ml of coffee.

		// Figure 15-3 shows the heap at this point in the program

		int origAmount = original.spillEntireContents();
		int copyAmount = copy.spillEntireContents();
		System.out.println("Original has " + origAmount + " ml of coffee.");
		System.out.println("Copy has " + copyAmount + " ml of coffee.");
	}
}