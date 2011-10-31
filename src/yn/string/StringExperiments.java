package yn.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringExperiments {
	public static void main (String[] args) throws Exception {
		split();
        shortToString();
        stringBuffer();
        stringBufferSubstring();
        stringFormat();
    }

	private static void split() {
		String x = "a^b^c";
		System.out.println(Arrays.toString(x.split("\\^")));		
	}

	private static void shortToString() throws UnsupportedEncodingException {
		int s1 = 0xd801;
        short s = (short)s1;
        String x = "" + (char)s;
        System.out.println("orig int: " + s1);
        System.out.println("orig short: " + s);
        System.out.println("length of string: " + x.length());
        System.out.println("value in string: " + (short)x.codePointAt(0));
        int s2 = ((short)x.codePointAt(0)) & 0xffff;
        System.out.println("restored value: " + s2);
        byte[] xb = x.getBytes("UTF8");
        System.out.println("encoded size: " + xb.length);
        String x2 = new String(xb, "UTF8");
        System.out.println("decode:" + x2);
        System.out.println("decode length:" + x2.length());
        int s3 = ((short)x2.codePointAt(0)) & 0xffff;
        System.out.println("value in string:" + s3);		
	}
	
	private static void stringBuffer() {
		StringBuffer sb = new StringBuffer();
		sb.append("test").append("\n");
		System.out.println("-->" + sb.substring(0, sb.length() - 1) + "<--");
	}

	private static void stringBufferSubstring() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("a");
		stringList.add("b");
		
		StringBuffer sb = new StringBuffer();
		sb.append("'");
		for (String str : stringList) {
			sb.append(str);
			sb.append("','");
		}
		System.out.println(sb.substring(0, sb.length()-2));		
	}
	
	private static void stringFormat() {
		String timeFormat = String.format("%02d:%02d:%02d", 12, 12, 23);
	}
}
