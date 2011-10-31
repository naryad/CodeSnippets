package yn.experiments;

import java.util.HashSet;
import java.util.Set;

/*
 * StringPair object: first and second values cannot be null.
 * StringPair object: first != second
 * StringPair object: is equal to another if both the first values and both the second values are equal.
 */
class StringPair {
	private String first;
	private String second;

	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
}

/*
 * StringPairGroup object: is equal to another if their StringPair sets exactly
 * match.
 */
public class StringPairGroup {
	Set<StringPair> stringPairs = null;

	public Set<StringPair> getStringPairs() {
		return stringPairs;
	}

	public void setStringPairs(Set<StringPair> stringPairs) {
		this.stringPairs = stringPairs;
	}

	/*
	 * pairParts.size() > 0 pairParts.size() is always an even number
	 */
	public static Set<StringPairGroup> getAllPossibleStringPairGroups( Set<String> pairParts) {
		Set<StringPairGroup> groups = new HashSet<StringPairGroup>();
		// logic that adds all possible StringPairGroups
		return groups;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}