package yn.datastruct;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private static class Node {
        private boolean isWord = false; // indicates a complete word
        private int prefixes = 0; // indicates how many words have the prefix
        private Map<Character, Node> children = new HashMap<Character, Node>(); // references to all possible children
    }

    private Node root = new Node();

    /**
     * Inserts a new word into the trie
     * @param word
     */
    public void insertWord(String word){
        if(searchWord(word) == true) return;

        Node current = root;
        for(char c : word.toCharArray()){
            if(current.children.containsKey(Character.valueOf(c))){
                Node child = current.children.get(Character.valueOf(c));
                child.prefixes++;

                current = child;
            }else{
                Node child = new Node();
                child.prefixes = 1;

                current.children.put(Character.valueOf(c), child);
                current = child;
            }
        }
        // we have reached the endof the word, hence mark it true
        // if during a search we reach the end of the search string and this
        // flag is still false, then the search string is not registered as a valid
        // word in the trie but is a prefix
        current.isWord = true;
    }

    /**
     * Searches for a word in the trie
     * @param word
     */
    public boolean searchWord(String word){
        Node current = root;
        for(char c : word.toCharArray()){
            if(current.children.containsKey(Character.valueOf(c))){
                current = current.children.get(Character.valueOf(c));
            }else{
                return false;
            }
        }
        // if at the end of the search of entire word the boolean variable is
        // still false means that the given word is not regitered as a valid
        // word in the trie, but is a prefix
        return current.isWord;
    }

    /**
     * Deletes a word from the trie
     * @param word
     */
    public void deleteWord(String word){
        if(searchWord(word) == false) return;

        Node current = root;
        for(char c : word.toCharArray()){
            Node child = current.children.get(Character.valueOf(c));
            if(child.prefixes == 1){
                current.children.remove(Character.valueOf(c));
                return;
            }else{
                child.prefixes--;
                current = child;
            }
        }
        // since the word is removed now, set the flag to false
        current.isWord = false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insertWord("ball");
        trie.insertWord("balls");
        trie.insertWord("bat");
        trie.insertWord("doll");
        trie.insertWord("dork");
        trie.insertWord("dorm");
        trie.insertWord("send");
        trie.insertWord("sense");

        // testing deletion
        System.out.println("Testing deletion : ");
        System.out.println(trie.searchWord("ball"));
        trie.deleteWord("ball");
        System.out.println(trie.searchWord("ball"));
        System.out.println(trie.searchWord("balls"));

        // testing insertion
        System.out.println("Testing insertion : ");
        System.out.println(trie.searchWord("dumb"));
        trie.insertWord("dumb");
        System.out.println(trie.searchWord("dumb"));
        trie.deleteWord("dumb");
        System.out.println(trie.searchWord("dumb"));
    }
} 
