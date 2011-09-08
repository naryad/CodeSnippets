package yn.graph;

import java.util.BitSet;

public class BitSetExample {
  public static void main(String args[]) {
    String names[] = { "Java", "Source", "and", "Support"};
    BitSet bits = new BitSet();
    for (int i = 0, n = names.length; i < n; i++) {
      if ((names[i].length() % 2) == 0) {
        bits.set(i);
      }
    }
    System.out.println(bits);
    System.out.println("Size : " + bits.size());
    System.out.println("Length: " + bits.length());
    for (int i = 0, n = names.length; i < n; i++) {
      if (!bits.get(i)) {
        System.out.println(names[i] + " is odd");
      }
    }
    BitSet bites = new BitSet(names.length);
    System.out.println("bites.cardinality()" + bites.cardinality());
    System.out.println("bites.length()" + bites.length());
    System.out.println("bites.size()" + bites.size());
    bites.set(0,4);
    bites.andNot(bits);
    System.out.println(bites);
  }
}