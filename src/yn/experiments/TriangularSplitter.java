package yn.experiments;

public class TriangularSplitter {

	  // asserts that the prefix of the string matches pattern
	  static String assertPrefix(String pattern) {
	    return "(?<=(?=^pattern).*)".replace("pattern", pattern);
	  }
	  // asserts that the entirety of the string matches pattern
	  static String assertEntirety(String pattern) {
	    return "(?<=(?=^pattern$).*)".replace("pattern", pattern);
	  }
	  // repeats an assertion as many times as there are dots behind current position
	  static String forEachDotBehind(String assertion) {
	    return "(?<=^(?:.assertion)*?)".replace("assertion", assertion);
	  }

	  public static void main(String[] args) {
	    final String TRIANGULAR_SPLITTER =
	      "(?x) (?<=^.) | measure (?=(.*)) check"
	        .replace("measure", assertPrefix("(?: notGyet . +NBefore +1After)*"))
	        .replace("notGyet", assertPrefix("(?! \\1 \\G)"))
	        .replace("+NBefore", forEachDotBehind(assertPrefix("(\\1? .)")))
	        .replace("+1After", assertPrefix(".* \\G (\\2?+ .)"))
	        .replace("check", assertEntirety("\\1 \\G \\2 . \\3"))
	        ;
	    System.out.println(TRIANGULAR_SPLITTER);
	    String text = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    System.out.println(
	        java.util.Arrays.toString(text.split(TRIANGULAR_SPLITTER))
	    );
	    // [a, bc, def, ghij, klmno, pqrstu, vwxyzAB, CDEFGHIJ, KLMNOPQRS, TUVWXYZ]
	  }
	}