package medium.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindingAnagram {

    public static boolean validAnagram(Map<Character, Integer> m1, Map<Character, Integer> m2) {
        for (Map.Entry<Character, Integer> m : m1.entrySet()) {
            if (!m2.containsKey(m.getKey())) {
                return false;
            } else if (!m.getValue().equals(m2.get(m.getKey()))) {
                return false;
            }
        }
        return true;
    }

    // WHOA Solved it in one pass
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    // https://leetcode.com/submissions/detail/721009242/
    // Though not performant
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagrams = new ArrayList<>();
        if(p.length() > s.length()) {
            return anagrams;
        }
        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        int l = 0, r = 0;
        for (char c : p.toCharArray()) {
            pMap.merge(c,          // key = char
                    1,                  // value to merge
                    Integer::sum);      // counting
        }
        while (r < s.length()) {
            if ((r-l) == p.length()) {
                if (validAnagram(pMap, sMap)) {
                    anagrams.add(l);
                }
                if (sMap.containsKey(s.charAt(l))) {
                    sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                }
                l = l + 1;
            }
            if (sMap.containsKey(s.charAt(r))) {
                sMap.put(s.charAt(r), sMap.get(s.charAt(r)) + 1);
            } else {
                sMap.put(s.charAt(r), 1);
            }
            r++;
        }
        if ((r-l) == p.length()) {
            if (validAnagram(pMap, sMap)) {
                anagrams.add(l);
            }
        }
        return anagrams;
    }

    // Same Problem
    // https://leetcode.com/problems/permutation-in-string/
    public boolean checkInclusion(String p, String s) {
        if(p.length() > s.length()) {
            return false;
        }
        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        int l = 0, r = 0;
        for (char c : p.toCharArray()) {
            pMap.merge(c,          // key = char
                    1,                  // value to merge
                    Integer::sum);      // counting
        }
        while (r < s.length()) {
            if ((r-l) == p.length()) {
                if (validAnagram(pMap, sMap)) {
                    return true;
                }
                if (sMap.containsKey(s.charAt(l))) {
                    sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                }
                l = l + 1;
            }
            if (sMap.containsKey(s.charAt(r))) {
                sMap.put(s.charAt(r), sMap.get(s.charAt(r)) + 1);
            } else {
                sMap.put(s.charAt(r), 1);
            }
            r++;
        }
        if ((r-l) == p.length()) {
            if (validAnagram(pMap, sMap)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
        System.out.println(findAnagrams("abab", "aba"));
        System.out.println(findAnagrams("abab", "abcde"));
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("aba", "a"));
    }
}
