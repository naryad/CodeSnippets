package yn.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int [] a = {23,4,5,7,8,45,8,8,9,97,0};
		System.out.println(Arrays.toString(mergeSort(a)));
	}
	
	private static int [] mergeSort(int [] input) {
		int arrayLength = input.length;
		if (arrayLength <= 1) {
			return input;
		}
		
		int mid = input.length >> 1;
		int [] left = Arrays.copyOfRange(input, 0, mid), right = Arrays.copyOfRange(input, mid, arrayLength);
		return merge(mergeSort(left), mergeSort(right));
	}

	private static int[] merge(int[] sortedLeft, int[] sortedRight) {
		int [] mergedArray = new int [sortedLeft.length + sortedRight.length];
		
		for (int i = 0, j = 0, k=0; i < mergedArray.length; i++) {
			if (j < sortedLeft.length && k < sortedRight.length) {
				if (sortedLeft[j] <= sortedRight[k]) {
					mergedArray[i] = sortedLeft[j];
					j++;
				} else {
					mergedArray[i] = sortedRight[k];
					k++;
				}
			} else if (j < sortedLeft.length) {
				mergedArray[i] = sortedLeft[j];
				j++;
			} else {
				mergedArray[i] = sortedRight[k];
				k++;
			}
		}
		return mergedArray;
	}
 }
