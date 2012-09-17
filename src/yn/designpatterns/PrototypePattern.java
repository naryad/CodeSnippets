package yn.designpatterns;

/**
 * Prototype class
 */
abstract class Prototype implements Cloneable {
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract void setX(int x);

	public abstract void printX();

	public abstract int getX();
}

/**
 * Implementation of prototype class
 */
class PrototypeImpl extends Prototype {
	int x;

	public PrototypeImpl(int x) {
		this.x = x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void printX() {
		System.out.println("Value :" + x);
	}

	public int getX() {
		return x;
	}
}

/**
 * Client code
 */
public class PrototypePattern {
	public static void main(String args[]) throws CloneNotSupportedException {
		Prototype prototype = new PrototypeImpl(1000);

		for (int i = 1; i < 10; i++) {
			Prototype tempotype = (Prototype) prototype.clone();

			// Usage of values in prototype to derive a new value.
			tempotype.setX(tempotype.getX() * i);
			tempotype.printX();
		}
	}
}

/*
 * *Code output**
 * 
 * Value :1000 Value :2000 Value :3000 Value :4000 Value :5000 Value :6000 Value
 * :7000 Value :8000 Value :9000
 */