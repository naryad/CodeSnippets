package yn.perf;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExperiments {
	public static void main(String[] args) {
		
		final int size = 1000000;
		final int [] array = new int [size];
		final Random rand = new Random();
		
		//parallelism is limited by number of available processors.
		System.out.println("Number of available processors = " + Runtime.getRuntime().availableProcessors());
		
		ExecutorService  executor = Executors.newFixedThreadPool(4);
		
		Thread qs1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs1");
		
		Thread qs2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs2");
		
		
		Thread qs3 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs3");
		
		
		Thread qs4 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs4");
		
		Thread qs5 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs5");
		
		Thread qs6 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs6");
		
		Thread qs7 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs7");
		
		
		Thread qs8 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs8");
		
		Thread qs9 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs9");
		
		Thread qs10 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs10");
		
		Thread qs11 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs11");
		
		Thread qs12 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < size; i++) {
					array[i] = rand.nextInt();
				}
				Arrays.sort(array);
			}
		}, "qs12");
		
		long startTime = System.currentTimeMillis();		
		
		executor.execute(qs1);
		executor.execute(qs2);
		executor.execute(qs3);
		executor.execute(qs4);
		executor.execute(qs5);
		executor.execute(qs6);
		executor.execute(qs7);
		executor.execute(qs8);
		executor.execute(qs9);
		executor.execute(qs10);
		executor.execute(qs11);
		executor.execute(qs12);
		
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
	
		// Wait until all threads are finish
		while (!executor.isTerminated()) { }
		
		System.out.println("Time taken for sorting 12 different sets of " 
				+ size + " random intengers each = " + (System.currentTimeMillis() - startTime));
	}
	
}
