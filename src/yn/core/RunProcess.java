package yn.core;

import java.io.IOException;

public class RunProcess {
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		Process pr = runtime.exec("processName.sh");
		pr.waitFor();
	}
}
