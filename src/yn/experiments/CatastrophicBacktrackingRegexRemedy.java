package yn.experiments;

public class CatastrophicBacktrackingRegexRemedy {
	public static void main(String[] args) {
		regexWithNoStackOverflow();
		regexCausingStackOverflow();
	}

	/**
	 * Stackoverflow is caused on a specific input using Tim's regex, in this
	 * case "A " repeated about 1000 times. This is because of the nested
	 * quantifier (?:[\\ ][\\w-]+ )*
	 */
	private static void regexCausingStackOverflow() {
		StringBuilder subjectStringBuilder = new StringBuilder();
		String subjectString = "A ";
		for (int i = 0; i < 1000; i++) {
			subjectStringBuilder.append(subjectString);
		}
		subjectStringBuilder.append("A");
		System.out.println("Input length :" + subjectStringBuilder.length());
		System.out.println("Input length :" + subjectStringBuilder.toString());
		// This causes stackoverflow exception on a string with 2001 characters
		// on my JVM (i was able to reproduce the error with 1601 characters)
		boolean foundMatch = subjectStringBuilder.toString().matches(
				"(?ix)        # Case-insensitive, multiline regex:\n"
						+ "^          # Start of string\n"
						+ "(?!        # Assert that it's impossible to match\n"
						+ " .*        # any number of characters\n"
						+ " (?:--|__) # followed by -- or __\n"
						+ ")          # End of lookahead assertion\n"
						+ "[A-Z0-9]+  # Match A-Z, a-z or 0-9\n"
						+ "(?:        # Match the following:\n"
						+ " [\\ ]     # a single space\n"
						+ " [\\w-]+   # followed by one or more alnums or -\n"
						+ ")*         # any number of times\n"
						+ "$          # End of string");
		// This will not be printed because of exception in the matches method
		System.out.println("Is match found? " + foundMatch);
	}

	/**
	 * Stack overflow can be avoided by modifying the negative lookahead and
	 * removing the nested quantifiers in the method above
	 */
	private static void regexWithNoStackOverflow() {
		StringBuilder subjectStringBuilder = new StringBuilder();
		String subjectString = "A ";
		for (int i = 0; i < 1000000; i++) {
			subjectStringBuilder.append(subjectString);
		}
		// returns match = true
		subjectStringBuilder.append("A");
		// uncomment following and it will give match = false (space in the end)
		// subjectStringBuilder.append("A A ");
		// uncomment following and it will give match = false (multiple spaces)
		// subjectStringBuilder.append("A  A");

		System.out.println("Input length :" + subjectStringBuilder.length());
		long start = System.currentTimeMillis();
		boolean foundMatch = subjectStringBuilder
				.toString()
				.matches(
						"(?ix)                      # Case-insensitive, multiline regex:\n"
						+ "^                        # Start of string\n"
						+ "(?!                      # Assert that it's impossible to match\n"
						+ " .*                      # any number of characters\n"
						+ " (?:--|__|\\s{2,}|\\s+$) # followed by -- or __ or more than a single"
						+ "                         # space or a space in the end\n"
						+ ")                        # End of lookahead assertion\n"
						+ "[A-Z0-9]+[\\w-]*          # Match A-Z, a-z or 0-9 followed by alnum \n"
						+ "(?:                      # Match the following:\n"
						+ " [\\s\\w-]               # followed by a space or alnum or -\n"
						+ ")*                       # any number of times\n"
						+ "$                        # End of string");
		System.out.println("time taken" + (System.currentTimeMillis() - start));
		System.out.println("Is match found? " + foundMatch);
	}
}
