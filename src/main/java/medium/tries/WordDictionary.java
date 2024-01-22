//package medium.tries;
//
//import sun.text.normalizer.Trie;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class WordDictionary {
//    Map<Character, TrieNode> trieMap;
//
//    public static class TrieNode {
//        Character ch;
//        Map<Character, TrieNode> nextNode;
//        Boolean isWord;
//
//        public TrieNode(Character ch) {
//            this.ch = ch;
//            nextNode = new HashMap<>();
//            isWord = false;
//        }
//    }
//
//    public WordDictionary() {
//        trieMap = new HashMap<>();
//    }
//
//    public void addWord(String word) {
//        Map<Character, TrieNode> current = trieMap;
//        TrieNode node = null;
//        for (Character c : word.toCharArray()) {
//            node = current.get(c);
//            if (node == null) {
//                node.nextNode = new HashMap<>();
//                current.put(c, node);
//            }
//            current = node.nextNode;
//        }
//        node.isWord = true;
//    }
//
//    public boolean search(String word) {
//        Map<Character, TrieNode> current = trieMap;
//        return searchRecursive(word, 0, current);
//    }
//
//    // bad
//    // .ad
//    // ba.
//    public boolean searchRecursive(String word, int index, Map<Character, TrieNode> nextNode) {
//        Map<Character, TrieNode> current = trieMap;
//        TrieNode node = null;
//        if (nextNode == null)
//            return false;
//        if (word.charAt(index) == '.') {
//            for (Character ch : nextNode.keySet()) {
//                return searchRecursive(word, index + 1, current.get(ch).nextNode);
//            }
//        }
//        node = current.get(word.charAt(index));
//        if (node != null && node.isWord)
//            return true;
//        if (node == null) {
//            return false;
//        }
//        return searchRecursive(word, index + 1, node.nextNode);
//
//    }
//}
