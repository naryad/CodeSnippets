package yn.core;

import java.util.*;  

public class SimpleLRU {  
  
  private static final int MAX_ENTRIES = 50;  
  
  private Map mCache = new LinkedHashMap(MAX_ENTRIES, .75F, true) {  
    protected boolean removeEldestEntry(Map.Entry eldest) {  
      return size() > MAX_ENTRIES;  
    }  
  };  
  
  public SimpleLRU() {  
    for(int i = 0; i < 100; i++) {  
      String numberStr = String.valueOf(i);  
      mCache.put(numberStr, numberStr);  
  
      mCache.get("0");
      if (i == 50) {
    	  System.out.println("Inserted 51 entries and least recently used element is 1 ..");
    	  System.out.println("mCache.get(0) : " + mCache.get("0"));
    	  System.out.println("mCache.get(1) : " + mCache.get("1"));
    	  System.out.println("mCache.get(2) : " + mCache.get("2"));
      }
        
    }  
  
    System.out.println("");  
  }  
  
  public static void main(String[] args) {  
    new SimpleLRU();  
  }  
}  