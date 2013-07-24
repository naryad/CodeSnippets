package yn.text;

import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.summariser.ISummariser;
import net.sf.classifier4J.summariser.SimpleSummariser;

public class Summarizer {
	public static void main(String[] args) throws ClassifierException {
		ISummariser summariser = new SimpleSummariser();
		String input = "Classifier4J is a java package for working with text. Classifier4J includes a summariser. A Summariser allows the summary of text. A Summariser is really cool. I don't think there are any other java summarisers.";
		String result = summariser.summarise(input, 1);
		System.out.println(result);
	}
}
