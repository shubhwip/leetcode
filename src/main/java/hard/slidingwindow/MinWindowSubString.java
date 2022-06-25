package hard.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubString {
    public static boolean inclusion(Map<Character, Integer> m1, Map<Character, Integer> m2)     {
        for (Map.Entry<Character, Integer> m : m1.entrySet()) {
            if (!m2.containsKey(m.getKey())) {
                return false;
            } else if (m.getValue() > (m2.get(m.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static String minWindow1(String s, String t) {
        int l=0,r=0;
        int m = s.length();
        int n = t.length();
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        // Creating Map of characters from string t
        for(char c: t.toCharArray()) {
            tMap.merge(c, 1, Integer::sum);
        }
        int minWindow = Integer.MAX_VALUE;
        int minWindowStart = 0;
        int minWindowEnd = 0;
        boolean found  = false;
        while(r < m) {
            while(r-l >= n) {
                if(inclusion(tMap, sMap)) {
                    if (minWindow > r - l) {
                        minWindow = r-l;
                        minWindowStart = l;
                        minWindowEnd = r;
                        found = true;
                    }
                    if (sMap.containsKey(s.charAt(l))) {
                        if (sMap.get(s.charAt(l)) == 1)
                            sMap.remove(s.charAt(l));
                        else
                            sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                    }
                    l++;
                }
                if(found)
                    l++;
                else
                    break;
            }
            if(r-l == n-1) {
                found = false;
            }
            sMap.merge(s.charAt(r), 1, Integer::sum);
            r++;
        }
        while(r-l > n) {
            if(inclusion(tMap, sMap)) {
                if(minWindow > r-l) {
                    minWindowStart = l;
                    minWindowEnd = r;
                    minWindow = r-l;
                }
                if (sMap.containsKey(s.charAt(l))) {
                    if (sMap.get(s.charAt(l)) == 1)
                        sMap.remove(s.charAt(l));
                    else
                        sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                }
                l++;
            }
        }
        return s.substring(minWindowStart, minWindowEnd);

    }

    // My First accepted Hard Problem at leetcode
    // In one go
    // Thanks to this https://leetcode.com/discuss/study-guide/1773891/Sliding-Window-Technique-and-Question-Bank
    public static String minWindow(String s, String t) {
        int l=0,r=0;
        int m = s.length();
        int n = t.length();
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        // Creating Map of characters from string t
        for(char c: t.toCharArray()) {
            tMap.merge(c, 1, Integer::sum);
        }
        int minWindow = Integer.MAX_VALUE;
        int minWindowStart = 0;
        int minWindowEnd = 0;
        while(r < m) {
            while(r-l >= n) {
                if(inclusion(tMap, sMap)) {
                    if (minWindow > r - l) {
                        minWindow = r-l;
                        minWindowStart = l;
                        minWindowEnd = r;
                    }
                    if (sMap.containsKey(s.charAt(l))) {
                        if (sMap.get(s.charAt(l)) == 1)
                            sMap.remove(s.charAt(l));
                        else
                            sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                    }
                    l++;
                } else
                    break;
            }
            sMap.merge(s.charAt(r), 1, Integer::sum);
            r++;
        }
        while(r-l >= n) {
            if(inclusion(tMap, sMap)) {
                if(minWindow > r-l) {
                    minWindowStart = l;
                    minWindowEnd = r;
                    minWindow = r-l;
                }
                if (sMap.containsKey(s.charAt(l))) {
                    if (sMap.get(s.charAt(l)) == 1)
                        sMap.remove(s.charAt(l));
                    else
                        sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                }
                l++;
            } else
                break;
        }
        return s.substring(minWindowStart, minWindowEnd);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));

    }
}
