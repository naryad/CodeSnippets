package yn.perf;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author NY
 */
public class QuickSortPerformance {
	public static void main(String[] args) throws InterruptedException {
		final int size = 100000000;
		final int [] array = new int [size];
		final int [] array1 = new int [size];
		final Random rand = new Random();
		Thread indigenousQuickSort = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				long start = System.nanoTime();
				quickSort(array, 0, size-1);
				//Arrays.sort(array);
				System.out.println("Time taken by indigenous sort " 
						+ (System.nanoTime() - start)/1000000000 + " seconds");
				
			}
		}, "indigenous-sort");
		indigenousQuickSort.start();
		
		Thread builtinQuickSort = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array1[i] = rand.nextInt();
				}
				long start = System.nanoTime();
				Arrays.sort(array1);
				System.out.println("Time taken by builtin sort " 
						+ (System.nanoTime() - start)/1000000000 + " seconds");
			}
		}, "built-in-sort");
		builtinQuickSort.start();

		Thread [] threads = new Thread[]{builtinQuickSort, indigenousQuickSort};
		for (Thread thread : threads) {
			thread.join();
		}
		
		//System.out.println(Arrays.toString(array));
	}
	
	/**
	 * Sorts the input array in place
	 * @param anArray
	 * @param position
	 * @param pivot
	 */
	public static void quickSort( int anArray[], int position, int pivot) {
	    if( position < pivot ) {
	        int x = anArray[pivot];
	        int i = position - 1; 

	        for(int j = position; j < pivot; j++ ) {

	            if(anArray[j] <= x) {
	                 i = i + 1;
	                 int temp =  anArray[i];
	                 anArray[i] = anArray[j];
	                 anArray[j] = temp;
	            } 

	        }
	        int temp = anArray[i+1];
	        anArray[i+1] = anArray[pivot];
	        anArray[pivot] = temp;
	        int q = i+1;

	        quickSort(anArray, position, q-1);
	        quickSort(anArray, q+1, pivot);

	    }

	}
}
