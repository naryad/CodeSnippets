package yn.text;

import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.IClassifier;
import net.sf.classifier4J.bayesian.BayesianClassifier;
import net.sf.classifier4J.bayesian.IWordsDataSource;
import net.sf.classifier4J.bayesian.SimpleWordsDataSource;
import net.sf.classifier4J.vector.HashMapTermVectorStorage;
import net.sf.classifier4J.vector.TermVectorStorage;
import net.sf.classifier4J.vector.VectorClassifier;

public class Classifier {
	public static void main(String[] args) throws ClassifierException {
		IWordsDataSource wds = new SimpleWordsDataSource();
		IClassifier classifier = new BayesianClassifier(wds);
		System.out.println( "Matches = " + classifier.classify("This is a sentence") );
		
		TermVectorStorage storage = new HashMapTermVectorStorage();
	    VectorClassifier vc = new VectorClassifier(storage);
	    vc.teachMatch("category", "hello there is this a long sentence yes it is blah blah hello.");
	    double match = vc.classify("category", "hello blah");
	    System.out.println(match);
	}
}
