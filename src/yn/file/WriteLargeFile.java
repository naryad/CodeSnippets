package yn.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteLargeFile {
	
	private static final String fileWritePath = "test.txt";
	private static final char CHARACTER = 'a';
	private static final long SIZE_IN_BYTES = 1024*1024*1024; //1 GB
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		writeLargeFile(SIZE_IN_BYTES);
	}
	
	/**
	 * @param sizeInBytes
	 * @throws IOException
	 */
	private static void writeLargeFile(long sizeInBytes) throws IOException {
		Writer writer = new BufferedWriter(new FileWriter(new File(fileWritePath)));
		
		for (long index = 0; index < sizeInBytes; index++) {
			writer.write(CHARACTER);
		}
		writer.flush();
	}
}
