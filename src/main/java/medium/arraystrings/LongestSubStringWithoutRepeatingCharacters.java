package medium.arraystrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStringWithoutRepeatingCharacters {


    // Accepted with below solution
    // But NonPerformant Code
    // Your runtime beats 13.39 % of java submissions.
    //    987 / 987 test cases passed.
    //            Status: Accepted
    //    Runtime: 89 ms
    //    Memory Usage: 39.9 MB
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int p = max > 0 ? i + max + 1 : (i + 1); // Interesting Optimization
            for (int j = p; j < s.length() + 1; j++) {
//                if (max == 95) // This optimization can bring solution to 16ms and accepted
//                    return max; // Idea is as max length can be only 95 with all the possible characters
                if(max == s.length())
                    return max; // Needed optimization
                if (!hasRepeat(s.substring(i, j))) {
                    max = (j - i) > max ? (j - i) : max;
                } else {
                    break; // This optimization was amazing
                }
            }
        }
        return max;
    }

    // Still TLE
    // But 986 / 987 test cases passed.
    public static int lengthOfLongestSubstring4(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                if (!hasRepeat(s.substring(i, j))) {
                    max = (j - i) > max ? (j - i) : max;
                } else {
                    break; // This optimization was amazing
                }
            }
        }
        return max;
    }

    private static boolean hasRepeat(String s) {
        Set<Character> charSet = new HashSet<>();
        // Inserting character of string into set
        for (int i = 0; i < s.length(); i++) {
            charSet.add(s.charAt(i));
        }
        // If length of set is equal to len of string
        // then it will have unique characters
        if (charSet.size() == s.length()) {
            return false;
        } else {
            return true;
        }
    }

    // Solution OK
    // Very Brute Force
    // Will TLE in large strings
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                if (!hasRepeat2(s.substring(i, j))) {
                    max = (j - i) > max ? (j - i) : max;
                }
            }
        }
        return max;
    }

    private static boolean hasRepeat2(String sub) {
        Map<Character, Integer> s = new HashMap<>();
        for (int i = 0; i < sub.length(); i++) {
            if (s.containsKey(sub.charAt(i)))
                return true;
            s.put(sub.charAt(i), 1);
        }
        return false;
    }

    // Not correct completely // Missing Cases`
    // 65461651651
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int i = 0;
        int max = Integer.MIN_VALUE;
        Map<Character, Boolean> map = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            boolean b = (map.containsKey(s.charAt(j)) && map.get(s.charAt(j)));
            if (j > 0 && s.charAt(j) != s.charAt(j - 1) && !b) {
                map.put(s.charAt(j), true);
                continue;
            }
            if ((j == (s.length() - 1)) || b) {
                int temp = (j - i);
                if (temp > max)
                    max = temp;
                i = j;
                map.clear();
            }
            map.put(s.charAt(j), true);
        }
        return s.length() - i > max ? s.length() - i : max;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("bbaaabc"));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
//        System.out.println(lengthOfLongestSubstring(null));
//        System.out.println(lengthOfLongestSubstring(""));
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        System.out.println(lengthOfLongestSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
//        System.out.println(lengthOfLongestSubstring("babcdaabcde"));
//        System.out.println(lengthOfLongestSubstring("abcdef"));
//        System.out.println(lengthOfLongestSubstring("aabbccddee"));
//        System.out.println(lengthOfLongestSubstring("aaaabbadfaaaaa"));
//        System.out.println(lengthOfLongestSubstring("abcdffdcbak"));
//        System.out.println(lengthOfLongestSubstring("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"));
//        System.out.println(lengthOfLongestSubstring("65461651651"));
    }
}
