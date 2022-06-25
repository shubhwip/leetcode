package medium.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-string-chain/
public class LongestStringChain {

    private Map<String, Integer> cache;

    public static boolean isPredecessor(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        if (s1Length == s2Length)
            return false;
        else if(s2Length > s1Length + 1)
            return false;
        int i = 0, j = 0;
        while (i < s1Length && j < s2Length ) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if(s1.charAt(i) != s2.charAt(j+1))
                    return false;
            }
            i++;
            j++;
        }
        return true;
    }

    // COPIED
    // https://leetcode.com/problems/longest-string-chain/discuss/458069/JAVA-Easy-Solution-with-Explanation-(MUST-READ)!
    public int longestStrChainTab(String[] words) {

        if(words == null || words.length == 0) return 0;
        int res = 0;
        Arrays.sort(words, (a, b)-> a.length()-b.length());  // Sort the words based on their lengths
        HashMap<String, Integer> map = new HashMap();       //Stores each word and length of its max chain.

        for(String w : words) {                             //From shortest word to longest word
            map.put(w, 1);                                  //Each word is atleast 1 chain long
            for(int i=0; i<w.length(); i++) {               //Form next word removing 1 char each time for each w
                StringBuilder sb = new StringBuilder(w);
                String next = sb.deleteCharAt(i).toString();
                next.substring(0,5);
                if(map.containsKey(next) && map.get(next)+1 > map.get(w))
                    map.put(w, map.get(next)+1);            //If the new chain is longer, update the map
            }
            res = Math.max(res, map.get(w));                //Store max length of all chains
        }
        return res;
    }

    public int longestStrChainMemo(String[] words) {
        cache = new HashMap<>();
        for(String word:words){
            cache.put(word, -1);
        }
        int ma = 0;
        for(String word:words){
            ma = Math.max(helper(word), ma);
        }
        return ma;
    }

    private int helper(String word){
        if(!cache.containsKey(word)){
            return 0;
        }
        Integer result = cache.get(word);
        if(result != -1){
            return result.intValue();
        }
        StringBuilder sb = new StringBuilder(word);
        int ma = 0;
        for(int i=0;i<word.length();i++){
            sb.deleteCharAt(i);
            ma = Math.max(helper(sb.toString()), ma);
            sb.insert(i, word.charAt(i));
        }
        cache.put(word, ma+1);
        return ma+1;
    }


    public static void main(String[] args) {
        System.out.println(isPredecessor("a", "b"));
        System.out.println(isPredecessor("a", "ab"));
        System.out.println(isPredecessor("a", "ba"));
        System.out.println(isPredecessor("a", "bac"));
        System.out.println(isPredecessor("bca", "bcda"));
        System.out.println(isPredecessor("bcd", "bcda"));
        System.out.println(isPredecessor("a", "cd"));
        System.out.println(isPredecessor("abc", "abac"));
        System.out.println(isPredecessor("cba", "bcad"));
    }
}
