package yn.core;

public class CloneExperiments {
	public static void main(String[] args) throws CloneNotSupportedException {
		A a = new A(20,30);
		A b = a.clone();		
		System.out.println(b.getI() + b.getJ());
	}
}

class A implements Cloneable {
	
	private int i;
	private int j;
	public A(int k, int l) {
		this.i = k;
		this.j = l;
	}
	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}
	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}
	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}
	
	@Override
	public A clone() throws CloneNotSupportedException {
		return (A) super.clone();
	}
}