package yn.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor1 {
	public static void main(String[] args) {

		/*if (args == null || args[0] == null) {
			LOGGER.error("Usage : java CommunityLoader comma_separated_verticals");
			System.out.println("Usage : java CommunityLoader comma_separated_verticals");
		}*/

		//run a job every fifteen minutes. spawn a single thread for this which will spawn one child  thread for each vertical.
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> result = scheduler.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				ExecutorService  executor = Executors.newFixedThreadPool(5);
				try {
					executor.execute(new Runnable() {
						
						@Override
						public void run() {
							int i = 0;
							while (i < 100) {
								System.out.println("Hello World!");								
								++i;
							}
						}
					});
				} catch (Exception ex) {
					
				} finally {
					// This will make the executor accept no new threads and finish all existing threads in the queue
					executor.shutdown();
					// Wait until all threads are finish
					while (!executor.isTerminated()) {

					}
				}
			}
		}, 0, 5, TimeUnit.SECONDS);
	
	}
}
