package yn.io;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils {

	public static void main(String args[]) throws IOException {
		usingBufferedReader();
		visitAllDirsAndFiles(new File("D:/ny"));
	}

	
	private static void usingBufferedReader() throws IOException {
		String s = "This is a &copy; copyright symbol "
				+ "but this is &copy not.\\n";
		char buf[] = new char[s.length()];
		s.getChars(0, s.length(), buf, 0);
		CharArrayReader in = new CharArrayReader(buf);
		BufferedReader f = new BufferedReader(in);
		int c;
		boolean marked = false;
		while ((c = f.read()) != -1) {
			switch (c) {
			case '&':
				if (!marked) {
					f.mark(32);
					marked = true;
				} else {
					marked = false;
				}
				break;
			case ';':
				if (marked) {
					marked = false;
					System.out.print("(c)");
				} else
					System.out.print((char) c);
				break;
			case ' ':
				if (marked) {
					marked = false;
					f.reset();
					System.out.print("&");
				} else
					System.out.print((char) c);
				break;
			default:
				if (!marked)
					System.out.print((char) c);
				break;
			}
		}
	}
	
	public static void copyLines() throws IOException {
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = 
                new BufferedReader(new FileReader("xanadu.txt"));
            outputStream = 
                new PrintWriter(new FileWriter("characteroutput.txt"));

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
	
	public static void copyCharacters() throws IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
	
	public static void copyBytes() throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
	
	// Process all files and directories under dir
	public static void visitAllDirsAndFiles(File dir) {
	    process(dir);

	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            visitAllDirsAndFiles(new File(dir, children[i]));
	        }
	    }
	}

	// Process only directories under dir
	public static void visitAllDirs(File dir) {
	    if (dir.isDirectory()) {
	        process(dir);

	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            visitAllDirs(new File(dir, children[i]));
	        }
	    }
	}

	private static void process(File dir) {
		System.out.println(dir.getPath());
	}

	// Process only files under dir
	public static void visitAllFiles(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            visitAllFiles(new File(dir, children[i]));
	        }
	    } else {
	        process(dir);
	    }
	}
}
