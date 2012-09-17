package yn.concurrent;

public class ThreadJoinDeadlock {
  public static void main(String[] args) throws Exception
  {
    System.out.println("joining");
    Thread.currentThread().join();
    System.out.println("i'm back"); // we never get to this point
  }
}