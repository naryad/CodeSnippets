package yn.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

class BeeperControl {
	private final java.util.concurrent.ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate( beeper, 10, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}

public class ScheduledExecutor {
	public static void main(String[] args) {
		new BeeperControl().beepForAnHour();
		System.out.println("here");
	}
}
