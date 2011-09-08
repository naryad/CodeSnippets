package yn.concurrent;

import java.util.concurrent.TimeUnit;

public class TestVolatile implements Runnable {
	private volatile boolean stopRequested = false;
	
	public void run() {
		while (!stopRequested) {
			System.out.println(stopRequested);
		}
	}

	public void stop() {
		stopRequested = true;
	}

	public static void main(String[] args) throws InterruptedException {
		TestVolatile tv = new TestVolatile();
		new Thread(tv, "Neverending").start();
		///Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);
		tv.stop();
	}
}
