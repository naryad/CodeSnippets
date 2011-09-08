package yn.concurrent;

/**
 * @author ny
 * 
 */
public class MultithreadedSingleton {
	private static final MultithreadedSingleton singleton = new MultithreadedSingleton();
	private MultithreadedSingleton() {
		synchronized (MultithreadedSingleton.class) {
			//initialize the variables			
		}
	}

	public static MultithreadedSingleton getSingleton() {
		return singleton;
	}

	private static class TestMultithreadedSingleton implements Runnable {
		@Override
		public void run() {
			MultithreadedSingleton sgt = getSingleton();
			System.out.println(sgt);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new TestMultithreadedSingleton()).start();
		new Thread(new TestMultithreadedSingleton()).start();
		new Thread(new TestMultithreadedSingleton()).start();
		new Thread(new TestMultithreadedSingleton()).start();
		new Thread(new TestMultithreadedSingleton()).start();
	}
}