package yn.experiments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

  public static void main(String[] args) {
    int times = 1000000;
    
    new TestCase() {
      public void run() {
        Pattern p = Pattern.compile("^(?!.*tuna)\\d\\w$");
        Matcher m = p.matcher("1wtuna");
        m.find();
      }
    }.times(times);
    
    Timer t = new Timer();
    
    t.start();
    new TestCase() {
      public void run() {
        Pattern p = Pattern.compile("^(?!.*tuna)\\d\\w$");
        Matcher m = p.matcher("1wtuna");
        m.find();
      }
    }.times(times);
    System.out.println("Test case 1: " + t.end() + " seconds");
    
    t.start();
    new TestCase() {
      public void run() {
        Pattern.matches("^(?!.*tuna)\\d\\w$", "1wtuna");
      }
    }.times(times);
    System.out.println("Test case 2: " + t.end() + " seconds");
    
    t.start();
    new TestCase() {
      public void run() {
        "1wtuna".matches("^(?!.*tuna)\\d\\w$");
      }
    }.times(times);
    System.out.println("Test case 3: " + t.end() + " seconds");
    
    
    t.start();
    new TestCase() {
      Pattern p = Pattern.compile("^(?!.*tuna)\\d\\w$");
      public void run() {
        Matcher m = p.matcher("1wtuna");
        m.find();
      }
    }.times(times);
    System.out.println("Test case 4: " + t.end() + " seconds");
    
    
    t.start();
    new TestCase() {
      Pattern p = Pattern.compile("^(?!.*tuna)\\d\\w$");
      Matcher m = p.matcher("");
      public void run() {
        m.reset("1wtuna");
        m.find();
      }
    }.times(times);
    System.out.println("Test case 5: " + t.end() + " seconds");
  }
  
  static abstract class TestCase {
    public abstract void run();
    public void times(int times) {
      for(int i = 0; i < times; i++) {
        run();
      }
    }
  }
  
  static class Timer {
    long s;
    
    public Timer() { }
    
    public long start() {
      s = System.currentTimeMillis();
      return s;
    }
    
    public double end() {
      return (System.currentTimeMillis() - s) / 1000.0;
    }
  }
}