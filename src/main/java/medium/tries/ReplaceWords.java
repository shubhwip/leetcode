package medium.tries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// First Pass - 2 / 126 test cases passed.
// Second Pass - Passed all cases
// https://leetcode.com/problems/replace-words/
public class ReplaceWords {

    public static Map<Character, TrieNode> root;

    public static class TrieNode {
        Character ch;
        Map<Character, TrieNode> nextNode;
        Boolean isWord;
        public TrieNode(Character ch) {
            this.ch = ch;
            this.nextNode = new HashMap<>();
            this.isWord  = false;
        }
    }

    public static void insert(String key) {
        Map<Character, TrieNode> current = root;
        TrieNode node = null;
        for(Character ch : key.toCharArray()) {
            node = current.get(ch);
            if(node == null) {
                node = new TrieNode(ch);
                current.put(ch, node);
            }
            current = node.nextNode;
        }
        node.isWord = true;
    }

    public static String searchAndReplace(String key) {
        StringBuilder sb = new StringBuilder();
        Map<Character, TrieNode> current = root;
        TrieNode node = null;
        for(Character ch: key.toCharArray()) {
            node = current.get(ch);
            if(node == null || node.isWord) {
                break;
            } else {
                sb.append(ch);
            }
            current = node.nextNode;
        }
        return sb.toString();
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for(String s: dictionary) {
            insert(s);
        }
        StringBuilder sb = new StringBuilder();
        for(String s: sentence.split("\\s")) {
            sb.append(searchAndReplace(s));
            sb.append(" ");
        }
        return sb.toString();
    }

}
