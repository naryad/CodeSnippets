package yn.experiments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericMethodInference {

	static <T> void test1(T t1, T t2) {
	}

	static <T> void test3(T t1, List<T> t2) {
	}

	static <T> void test4(List<T> t1, List<T> t2) {
	}

	public static void main(String[] args) {

		List<Object> c = new LinkedList<Object>();
		List<? extends Object> d = new ArrayList<Integer>();

		test1("Hello", new Integer(1)); // ok clause (1)
		GenericMethodInference.<Object>test1("Hello", new Integer(1)); // ok clause (2)
		test3("Hello", c); // ok clause (3)
		//test4(d,d); // clause (4) Error due to different type capture
	}
}