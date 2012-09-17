package yn.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	//Size of the test file is 1GB.
	private static final String INPUT_FILE_PATH = "test.txt";
	private static final String OUTPUT_FILE_PATH_FOR_FILE_CHANNEL = "test1.txt";
	private static final String OUTPUT_FILE_PATH_FOR_BUFFERED_READER = "test2.txt";
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

	public static void main(String[] args) throws Exception {
		Timer t = new Timer();

		t.start();
		copyFileUsingBufferedReader();
		System.out.println("Copying file using buffered reader takes " + t.end() 
                                                         + " milliseconds");

		t.start();
		copyFileUsingFileChannel();
		System.out.println("Reading file using file channel takes " + t.end() 
                                                         + " milliseconds");
		
		t.start();
		copyFileUsingFileChannelTransferTo();
		System.out.println("Reading file using file channel transferTo takes " + t.end() 
                + " milliseconds");
	}

	private static void copyFileUsingFileChannel() throws IOException {
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			source = new FileInputStream(new File(INPUT_FILE_PATH)).getChannel();
			destination = new FileOutputStream(
                        new File(OUTPUT_FILE_PATH_FOR_FILE_CHANNEL)).getChannel();
			
			//This fails with Map Failed exception on large files
			//destination.transferFrom(source, 0, source.size());
			
			ByteBuffer buf = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
	        while((source.read(buf)) != -1) {
	                buf.flip();
	                destination.write(buf);
	                buf.clear();
	        }
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	private static void copyFileUsingBufferedReader() throws IOException {

		BufferedInputStream source = new BufferedInputStream(
                new FileInputStream(new File(INPUT_FILE_PATH)));
		BufferedOutputStream destination = new BufferedOutputStream(
                new FileOutputStream(new File(OUTPUT_FILE_PATH_FOR_BUFFERED_READER)));
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

		try {
	        int n = 0;
	        while (-1 != (n = source.read(buffer))) {
	        	destination.write(buffer, 0, n);
	        }
	        destination.flush();
		} finally {
			if (source != null) {
				source.close();	
			}
			if (destination != null) {
				destination.close();				
			}
		}
	}

	private static void copyFileUsingFileChannelTransferTo() throws IOException {
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			source = new FileInputStream(new File(INPUT_FILE_PATH)).getChannel();
			destination = new FileOutputStream(
                        new File(OUTPUT_FILE_PATH_FOR_FILE_CHANNEL)).getChannel();
			long size = source.size();
			
			//if this works use this. this is failing in windows 
			//source.transferTo( 0, size, destination );
            
            long position = 0;
            while ( (position += source.transferTo( position, DEFAULT_BUFFER_SIZE, destination )) < size )
               ;
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
}

class Timer {
	long s;

	public Timer() {
	}

	public long start() {
		s = System.currentTimeMillis();
		return s;
	}

	public long end() {
		return System.currentTimeMillis() - s;
	}
}