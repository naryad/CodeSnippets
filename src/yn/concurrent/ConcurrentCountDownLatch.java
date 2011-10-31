package yn.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCountDownLatch {

	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();
		int numberOfThreads = 2;
		final ExecutorService executor = Executors.newFixedThreadPool(5);
		final CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

		for (int i = 0; i < numberOfThreads; i++) {
			executor.submit(new Runnable() {
				public void run() {
					int arraySize = 50000000;
					int [] array = new int[arraySize];
					Random rand = new Random();
					for (int index = 0; index < arraySize; index++) {
						array[index] = rand.nextInt();
					}
					Arrays.sort(array);
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();
		System.out.println("All threads finished now after " + (System.nanoTime() - start)/1000000000 + " seconds.");
		executor.shutdown();
	}
}