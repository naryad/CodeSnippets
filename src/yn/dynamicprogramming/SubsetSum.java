package yn.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
public class SubsetSum {

	public static boolean isSubsetSum(int[] input, int arrayLen,
			int remainingSum) {
		// Base Cases
		if (remainingSum == 0)
			return true;

		if (arrayLen == 0 && remainingSum != 0)
			return false;

		// If last element is greater than sum, then ignore it
		if (input[arrayLen - 1] > remainingSum)
			return isSubsetSum(input, arrayLen - 1, remainingSum);

		/*
		 * else, check if sum can be obtained by any of the following (a)
		 * including the last element (b) excluding the last element
		 */
		return isSubsetSum(input, arrayLen - 1, remainingSum)
			|| isSubsetSum(input, arrayLen - 1, remainingSum - input[arrayLen - 1]);
	}

	public static void main(String[] args) {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		int sum = 60;

		if (isSubsetSum(set, set.length, sum))
			System.out.println("Found a subset with given sum");
		else
			System.out.println("No subset with given sum");
		
		if (isSubsetSumDP(set, set.length, sum))
			System.out.println("Found a subset with given sum");
		else
			System.out.println("No subset with given sum");
		
		sum = 59;
		if (isSubsetSum(set, set.length, sum))
			System.out.println("Found a subset with given sum");
		else
			System.out.println("No subset with given sum");
		
		if (isSubsetSumDP(set, set.length, sum))
			System.out.println("Found a subset with given sum");
		else
			System.out.println("No subset with given sum");
	}

	public static boolean isSubsetSumDP(int array[], int n, int sum) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		map.put(0, true);

		for (int index = 0; index < n; index++) {

			List<Integer> toBeAdded = new ArrayList<Integer>();
			for (Entry<Integer, Boolean> entry : map.entrySet()) {
				Integer key = entry.getKey();
				if (key + array[index] <= sum) {
					toBeAdded.add(key + array[index]);
				}
			}
			for (Integer number : toBeAdded) {
				map.put(number, true);
			}

			if (map.get(sum) != null) {
				return true;
			}
		}

		return false;
	}
}
