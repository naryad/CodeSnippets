package yn.text;

import org.knallgrau.utils.textcat.TextCategorizer;

public class LanguageGuesser {
	public static void main(String[] args) {
		String category = "please enter a command line argument.";
        TextCategorizer guesser = new TextCategorizer();
        if(args.length > 0) {
           category = guesser.categorize(args[0]);
        }
        System.out.println(category);
	}
}
