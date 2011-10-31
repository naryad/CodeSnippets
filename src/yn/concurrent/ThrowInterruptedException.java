package yn.concurrent;

public class ThrowInterruptedException {
    public static void main(String[] args) {
       final Thread bThread1 = new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               Thread.sleep(20000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
       });
       bThread1.start();

       final Thread bThread2 = new Thread(new Runnable() {
         @Override
         public void run() {
            try {
               bThread1.join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
       });
       bThread2.start();

       bThread2.interrupt();

    }
}