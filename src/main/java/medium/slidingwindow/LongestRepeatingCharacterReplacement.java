package medium.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestRepeatingCharacterReplacement {

    // NOT MINE
    // EFFICIENT WITHOUT EXTRAA SPACE COMPLEXITY
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public int characterReplacementMySolution(String s, int k) {
        Set<Character> set = new HashSet<>();
        for(char c: s.toCharArray()) {
            set.add(c);
        }
        int max = Integer.MIN_VALUE;
        for(Character c: set) {
            max = Math.max(longestSubstring(c, s, k), max);
        }
        return max;
    }
    // ACCEPTED
    // Runtime: 17 ms
    // Memory Usage: 43.8 MB
    // Extraa Space Complexity of Set
    public int longestSubstring(char c, String s, int k) {
        int l=0,r=0;
        while(r<s.length()) {
            if(s.charAt(r) != c)
                k--;
            if(k<0 && s.charAt(l++) != c) {
                k++;
            }
            r++;
        }
        return r-l;
    }

    public static void main(String[] args) {

    }
}
