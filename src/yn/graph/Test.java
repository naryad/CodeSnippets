package yn.graph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test {
	public static void main(String[] args) throws IOException {
		if (args.length != 0) {
			String [] arg0split = args[0].split(",");
			System.out.println(arg0split[0]);
		}

		System.out.println("fasdf lang:en".replaceAll("\\s*lang:en\\s*", ""));
		characterAndHex();
		printWriterPrintStream();
		
		concurrentStuff();
		
		System.out.println(reverseString("This world is whatever"));
		System.out.println(kRotateWord("ABCDEF", 3));
		
		exploreSystemAndRuntime();
		loadClass();
		
		priorityQueue();
	}
	
	private static void concurrentStuff() {
		AtomicBoolean fal = new AtomicBoolean(false);
		fal.compareAndSet(false, true);
		System.out.println(fal);
		
		
		List l = new ArrayList<String>();
		l.add("narendra");
		getUnmodifiableList(l);
	}


	private static void characterAndHex() {
		System.out.println(Long.MAX_VALUE);
		System.out.println("charset that is used " + Charset.defaultCharset()); //getting the charset
		System.out.println("hex value of \u5185\u7530" + toHexArray("\u5185\u7530".getBytes()));
		
		
		//to convert a string to a byte
		System.out.println( Integer.toHexString(Integer.parseInt("1e", 16)));
		
		char n = 'n';
		System.out.println("'" + n + "'" + " --> " +(int)n + " --> " + toHex(String.valueOf(n)));
				
	}


	/**
	 * This keeps the leading zeros
	 * @param bytes
	 * @return
	 */
	private static String toHexArray(byte[] bytes) {
		String[] hexArray = { "00","01","02","03","04","05","06","07","08","09","0A","0B","0C","0D","0E","0F", "10","11","12","13","14","15","16","17","18","19","1A","1B","1C","1D","1E","1F", "20","21","22","23","24","25","26","27","28","29","2A","2B","2C","2D","2E","2F", "30","31","32","33","34","35","36","37","38","39","3A","3B","3C","3D","3E","3F", "40","41","42","43","44","45","46","47","48","49","4A","4B","4C","4D","4E","4F", "50","51","52","53","54","55","56","57","58","59","5A","5B","5C","5D","5E","5F", "60","61","62","63","64","65","66","67","68","69","6A","6B","6C","6D","6E","6F", "70","71","72","73","74","75","76","77","78","79","7A","7B","7C","7D","7E","7F", "80","81","82","83","84","85","86","87","88","89","8A","8B","8C","8D","8E","8F", "90","91","92","93","94","95","96","97","98","99","9A","9B","9C","9D","9E","9F", "A0","A1","A2","A3","A4","A5","A6","A7","A8","A9","AA","AB","AC","AD","AE","AF", "B0","B1","B2","B3","B4","B5","B6","B7","B8","B9","BA","BB","BC","BD","BE","BF", "C0","C1","C2","C3","C4","C5","C6","C7","C8","C9","CA","CB","CC","CD","CE","CF", "D0","D1","D2","D3","D4","D5","D6","D7","D8","D9","DA","DB","DC","DD","DE","DF", "E0","E1","E2","E3","E4","E5","E6","E7","E8","E9","EA","EB","EC","ED","EE","EF", "F0","F1","F2","F3","F4","F5","F6","F7","F8","F9","FA","FB","FC","FD","FE","FF"};
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(hexArray[0xFF & b]);
		}
		return sb.toString();
	}


	private static void printWriterPrintStream() {
		PrintStream ps;
		byte b = 'c';
		try {
			ps = new PrintStream("serial");
			ps.print(b);//prints int value of byte b
			ps.append('c'); //prints character c
			ps.append("wtf");//prints string wtf
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			PrintWriter pw = new PrintWriter("serial");
			pw.write("asdfas");
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}


	private static void priorityQueue() {
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		p.add(10);
		p.add(0);
		p.add(1);
		System.out.println("Priority queue e1 " + p.remove());
		System.out.println("Priority queue e2 " + p.remove());
		System.out.println("Priority queue e3 " + p.remove());
	}


	private static void loadClass() {
		try {
			Class c  = Class.forName("java.util.ArrayList");
			Class d = ClassLoader.getSystemClassLoader().loadClass("java.util.ArrayList");
			
			ArrayList a = (ArrayList)c.newInstance();
			a.add("a");
			System.out.println(c.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void exploreSystemAndRuntime() throws IOException {
		System.gc();//suggesting jvm to run garbaze collection
		System.out.println(Runtime.getRuntime().freeMemory());
		System.getProperty("user.dir");
		System.runFinalization();
		System.console();
		System.out.println(System.getenv());
		Runtime.getRuntime().totalMemory();
		Runtime.getRuntime().freeMemory();
		System.out.println("Available processors " + Runtime.getRuntime().availableProcessors());
		//Runtime.getRuntime().exec("wtf");
		
	}
	private static String kRotateWord(String string, int i) {
		Queue s = new  ArrayDeque();
		for (int j = 0; j < i; j++) {
			s.add(string.charAt(j));
		}
		StringBuffer sbuf = new StringBuffer(string.substring(i));
		while (!s.isEmpty()) {
			sbuf.append((Character)s.remove());
		}
		return sbuf.toString();
	}
	private static String reverseString(String string) {
		String reverseline = "This world is whatever";
		Stack<String> s = new java.util.Stack<String>();
		String [] reverse = reverseline.split("\\s");
		for (String st : reverse) {
			s.push(st);
		}
		StringBuffer sbuf = new StringBuffer();
		while (!s.isEmpty()) {
			sbuf.append((String)s.pop()+ " ");
		}
		reverseline = sbuf.substring(0, sbuf.length() - 1);
		return reverseline;
	}
	public static String toHex(String arg) {
	    try {
			return String.format("%x", new BigInteger(arg.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return arg;
		}
	}
	
	public static List getUnmodifiableList(List l) {
		return Collections.unmodifiableList(l);
	}
}
