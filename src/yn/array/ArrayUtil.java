package yn.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ArrayUtil {
	public static void main(String[] args) {
		//reversing array
		int [] a = {1,35,7,78,9};
		Collections.shuffle(Arrays.asList(a));
		System.out.println("Shuffled " + Arrays.toString(a));
		System.out.println(Arrays.toString(reverseArray(a)));
		
		//shuffle array
		int [] b = {1,2,3,4, 5,6,7,8};
		System.out.println(Arrays.toString(shuffleArray(b)));
		
		System.out.println(Arrays.toString(getRandomPermutationOfIntegers(100)));
	}
	
	private static int [] reverseArray(int [] arr){
	    for(int i=0, len = arr.length-1, mid = arr.length>>1;i<mid;i++){
	        exchange(arr, i,len-i);
	    }
	    return arr;
	}
	
	private static int [] shuffleArray(int [] arr){
	    Random rand = new Random();
	    for (int i = 0; i < arr.length; i++) {
	    	int j = rand.nextInt(arr.length);
	    	if (i != j) {
	    		exchange(arr, i, j); 
	    	}
	    }
		
	    return arr;
	}
	
	private static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;		
	}

	public static int[] getRandomPermutationOfIntegers(int size) {
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = i;
		}
		// shuffle the array
		Random rand = new Random();
		for (int i = 0; i < data.length; i++) {
			int j = rand.nextInt(data.length);
			if (i != j) {
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}

		return data;
	}
}
