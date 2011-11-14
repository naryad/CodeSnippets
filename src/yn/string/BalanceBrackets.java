package yn.string;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class BalanceBrackets {
	private Stack<Character> symbolStack;

	public void balance(String inputText) {
		symbolStack = new Stack<Character>();
		for (int index = 0; index < inputText.length(); index++) {
			char currentSymbol = inputText.charAt(index);
			switch (currentSymbol) {
			case '(':
			case '[':
			case '{': 
				symbolStack.push(currentSymbol);
				break;
			
			case ')':
			case ']':
			case '}':
				if (!symbolStack.isEmpty()) {
					char symbolStackTop = symbolStack.pop();
					if ((currentSymbol == '}' && symbolStackTop != '{')
							|| (currentSymbol == ')' && symbolStackTop != '(')
							|| (currentSymbol == ']' && symbolStackTop != '[')) {
						System.out.println("Unmatched closing bracket while parsing " + currentSymbol + " at " + index+1);
						return;
					}
				} else {
					System.out.println("Extra closing bracket while parsing " + currentSymbol + " at character " + index+1);
					return;
				}
				break;
			default:
				break;
			}
		}
		if (!symbolStack.isEmpty())
			System.out.println("Insufficient closing brackets after parsing the entire input text");
		else 
			System.out.println("Brackets are balanced");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		String input = null;
		StringBuilder sb = new StringBuilder();
		while ((input = in.readLine()) != null) {
			sb.append(input);
		}
		new BalanceBrackets().balance(sb.toString());
	}
}
