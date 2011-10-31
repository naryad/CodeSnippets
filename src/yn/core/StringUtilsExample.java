package yn.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.*;

public class StringUtilsExample {
	public static void main(String[] args) {
		List<String> listOfStrings = new ArrayList<String>();
		listOfStrings.add("1");
		listOfStrings.add("11");
		listOfStrings.add("111");
		listOfStrings.add("1111");
		listOfStrings.add("11111");
		String csv = "'" + join(listOfStrings, "','") + "'";
		System.out.println("'" + join(listOfStrings, "','") + "'");
		
		System.out.println(Arrays.asList(csv.split(",")));
		System.out.println(StringUtils.leftPad("999", 10,'0'));
		String x = "  ";
		System.out.println(isBlank(x));
		
		String y = "fasdfasd";
		System.out.println(y.matches(".*as.*"));
	}
}
