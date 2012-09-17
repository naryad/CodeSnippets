package yn.string;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringExperiments {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main (String[] args) throws Exception {
		split();
        shortToString();
        stringBuffer();
        stringBufferSubstring();
        stringFormat();
        stringPool();
        stringCharSet();
        String str = ".egaugnal detneiro tcejbo na si avaJ";
        
        System.out.println(reverseString(str));
        System.out.println(reverseString1(str));
        System.out.println(reverseString2(str));
    }
	public static String reverseString2(String str){
		int len = str.length();
	    return len<=1 ? str : (
	        reverseString2(str.substring(len/2))+
	        reverseString2(str.substring(0, len/2))
	    );
	}
	
	public static String reverseString1(String s){
	    if (s.length() == 0) 
	         return s;

	    return reverseString1(s.substring(1)) + s.charAt(0);
	}
	
	public static String reverseString(String str) {
	    return reverseString("", str);
	}
	
	private static String reverseString(String reversed, String forward) {
	    return forward.equals("") ? reversed : (
	         reverseString(reversed+forward.charAt(forward.length()-1), forward.substring(0,forward.length()-1)) 
	    );
	}

	private static void stringCharSet() {
		// Create the encoder and decoder for ISO-8859-1
		System.out.println("Default Charset=" + Charset.defaultCharset());
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetDecoder decoder = charset.newDecoder();
		CharsetEncoder encoder = charset.newEncoder();

		try {
			// Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
			// The new ByteBuffer is ready to be read.
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("a string"));

			// Convert ISO-LATIN-1 bytes in a ByteBuffer to a character
			// ByteBuffer and then to a string.
			// The new ByteBuffer is ready to be read.
			CharBuffer cbuf = decoder.decode(bbuf);
			String s = cbuf.toString();
		} catch (CharacterCodingException e) {
		}
	}

	private static void stringPool() {
		String a = "foo"; 
		String b = "food".substring(0,3); 
		String c = b.intern();
		String d = "foodie".substring(0,3);
		String e = "foo";
		String f = new String("foo");
		
		//true
		System.out.println(a == c);
		System.out.println(b);
		System.out.println(a == b);
		//false
		System.out.println(a == d);
		System.out.println(b == d);
		
		//true
		System.out.println(a == e);
		
		//false
		System.out.println(a == f);
	}

	/**
	 * splits on ^ character
	 */
	private static void split() {
		String x = "a^b^c";
		System.out.println(Arrays.toString(x.split("\\^")));		
	}

	/**
	 * @throws UnsupportedEncodingException
	 */
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
	
	/**
	 * String buffer experiments
	 */
	private static void stringBuffer() {
		StringBuffer sb = new StringBuffer();
		sb.append("test").append("\n");
		System.out.println("-->" + sb.substring(0, sb.length() - 1) + "<--");
	}

	/**
	 * String buffer substring.
	 */
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
	
	/**
	 * String format usage.
	 */
	private static void stringFormat() {
		String timeFormat = String.format("%02d:%02d:%02d", 12, 12, 23);
	}
}
