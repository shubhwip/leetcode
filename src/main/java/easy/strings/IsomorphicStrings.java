package easy.strings;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    // Accepted but worst performing code
    // Runtime: 31 ms, faster than 10.40% of Java online submissions for Isomorphic Strings.
    // Memory Usage: 42.8 MB, less than 56.90% of Java online submissions for Isomorphic Strings.
    public static boolean isIsomorphic(String s, String t) {
        if(s.length() < 2) {
            return true;
        }
        int i = s.length()-1;
        Map<Character, Character> isomorphic = new HashMap<>();
        isomorphic.put(s.charAt(0), t.charAt(0));
        int j=1;
        while(j<=i) {
            if(isomorphic.containsKey(s.charAt(j))) {
                if(isomorphic.get(s.charAt(j)) != t.charAt(j)) {
                    return false;
                }
            } else if (isomorphic.containsValue(t.charAt(j))) {
                return false;
            }
            isomorphic.put(s.charAt(j), t.charAt(j));
            j++;
        }
        return true;
    }

    // 35 / 43 test cases passed.
    // "bbbaaaba"
    // "aaabbbba"
//    public static boolean isIsomorphic(String s, String t) {
//        if (s.length() < 2) {
//            return true;
//        }
//        Set<Character> s1 = new LinkedHashSet<>();
//        Set<Character> s2 = new LinkedHashSet<>();
//        int i = s.length() - 1;
//        while (i >= 0) {
//            s1.add(s.charAt(i));
//            s2.add(t.charAt(i));
//            i--;
//        }
//        if (s1.size() != s2.size())
//            return false;
//        return true;
//    }

    // 36 / 43 test cases passed.
    //Input:
    //"badc"
    //"baba"
    //Output:
    //true
    //Expected:
    //false
    /*
    public boolean isIsomorphic(String s, String t) {
        if(s.length() < 2) {
            return true;
        }
        int i = s.length()-1;
        Map<Character, Character> isomorphic = new HashMap<>();
        isomorphic.put(s.charAt(0), t.charAt(0));
        int j=1;
        while(j<=i) {
            if(isomorphic.containsKey(s.charAt(j))) {
                if(isomorphic.get(s.charAt(j)) != t.charAt(j)) {
                    return false;
                }
            }
            isomorphic.put(s.charAt(j), t.charAt(j));
            j++;
        }
        return true;
    } */

    public static void main(String[] args) {
        System.out.println(isIsomorphic("baba", "badc"));
        System.out.println(isIsomorphic("badc", "baba"));
        System.out.println(isIsomorphic("bbbaaaba", "aaabbbba"));
    }
}
