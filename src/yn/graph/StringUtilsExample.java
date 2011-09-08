package yn.graph;

import java.util.ArrayList;
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
		System.out.println("'" + join(listOfStrings, "','") + "'");
		
		System.out.println(StringUtils.leftPad("999", 10,'0'));
		String x = null;
		System.out.println(isBlank(x));
	}
}
