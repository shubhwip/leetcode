package medium.tries;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/explore/learn/card/trie
public class MapSum {
    private Map<Character, TrieNode> trieNodeMap;

    public static class TrieNode {
        Character ch;
        Map<Character, TrieNode> nextNodeMap;
        Integer val;

        public TrieNode( Character ch ) {
            this.ch = ch;
            this.nextNodeMap = new HashMap<>();
        }
    }

    public MapSum() {
        this.trieNodeMap = new HashMap<>();
    }

    public void insert(String key, int val) {
        insertKey( key.toCharArray(), val );
    }

    public int sum(String prefix) {
        TrieNode node = getLastNode( prefix );
        if( node == null ) return 0;
        return getSum( node );
    }

    private TrieNode getLastNode( String prefix ) {
        TrieNode node = null;
        Map<Character, TrieNode> trieMap = trieNodeMap;

        for( char ch : prefix.toCharArray() ) {
            node = trieMap.get( ch );
            if( node == null ) return null;
            trieMap = node.nextNodeMap;
        }
        return node;
    }

    private void insertKey( char[] keyArr, Integer val ) {
        Map<Character, TrieNode> trieMap = trieNodeMap;
        TrieNode node = null;
        for( char ch : keyArr ) {
            node = trieMap.get( ch );
            if( node == null ) {
                node = new TrieNode( ch );
                trieMap.put( ch, node );
            }
            trieMap = node.nextNodeMap;
        }
        node.val = val;
    }

    private int getSum( TrieNode node ) {
        if( node.nextNodeMap == null ) {
            return 0;
        }
        Map<Character, TrieNode> tmpMap = node.nextNodeMap;
        int totalSum = node.val == null ? 0 : node.val;
        for( TrieNode n : tmpMap.values() ) {
            totalSum += getSum( n );
        }

        return totalSum;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */