package yn.graph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void regex1() {
		Pattern p = Pattern.compile("t(est)");
		String candidateString = "This is a test. This is another test.";
		Matcher matcher = p.matcher(candidateString);
		// Find group number 0 of the first find
		matcher.find();
		String group_0 = matcher.group(0);
		String group_1 = matcher.group(1);
		System.out.println("Group 0 " + group_0);
		System.out.println("Group 1 " + group_1);
		System.out.println(candidateString);
		System.out.println(matcher.replaceAll("$1"));
	}

	public static void regex2() {
		String patternStr = "\\((\\w+)\\)";
		String replaceStr = "<$1>";
		Pattern pattern = Pattern.compile(patternStr);

		CharSequence inputStr = "a (b c) d (ef) g";
		Matcher matcher = pattern.matcher(inputStr);
		String output = matcher.replaceAll(replaceStr);
		System.out.println(output);
	}

	public static void main(String[] args) {
		regex1();
		regex2();
		splitTest();
	}

	private static void splitTest() {
		String str = "x , y";
		for (String output : str.split("\\s*,\\s*")){
			System.out.println(output);
		}
	}
}
