package yn.datastruct;

import java.util.ArrayList;
import java.util.List;

public class QuickSelect {
	private static int findKthSmallest(Integer [] a, int k) {
		int pivot = a.length/2;
		int pivotElement = a[pivot];
		List<Integer> small = new ArrayList<Integer>();
		List<Integer> big = new ArrayList<Integer>();
		for (int x : a) {
			if (x < pivotElement) {
				small.add(x);
			} else if (x > pivotElement) {
				big.add(x);
			}
		}
		if (k <= small.size()) {
			return findKthSmallest(small.toArray(new Integer[small.size()]), k);
		} else if (k > a.length - big.size()) {
			return findKthSmallest(big.toArray(new Integer[big.size()]), 
					k - (a.length - big.size()));
		} else 
			return pivotElement;
	}

	public static void main(String[] args) {
		System.out.println(findKthSmallest(new Integer[] {1, 2, 3, -1}, 3));
	}
}
