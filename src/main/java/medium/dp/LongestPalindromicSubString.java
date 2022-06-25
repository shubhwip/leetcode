package medium.dp;

public class LongestPalindromicSubString {
    // Brute Force
    // Using Two Loops we can determine longest palindromic substring with check palindrome function
    public static boolean isPalindrome(String s) {
        int len = s.length();
        int mid = len % 2 == 0 ? (len / 2) + 1 : len / 2;
        for (int i = 0; i < mid; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1))
                return false;
        }
        return true;
    }

    // TLE
    // 109 / 180 test cases passed.
    public static String longestPalindromeBF(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isPalindrome(s.substring(i, j + 1)))
                    if (max < (i + j + 1)) {
                        start = i;
                        end = j;
                        max = i + j + 1;
                    }
            }
        }
        return s.substring(start, end + 1);
    }

    public static String longestPalindromeDP(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int start = 0;
        int end = 0;
        int len = s.length();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = 1;
                start = i;
                end = i + 1;
                max = 2;
            } else
                dp[i][i + 1] = 0;
        }
        for (int i = 2; i < len; i++) {
            for (int j = 0; j < len-1; j++) {
                if (s.charAt(i) == s.charAt(j) && dp[j + 1][i - 1] == 1) {
                    if (max < i - j + 1) {
                        start = j;
                        end = i;
                        max = i - j + 1;
                    }
                    dp[j][i] = 1;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static String longestPalindrome(String s) {
        //return longestPalindromeBF(s);
        return longestPalindromeDP(s);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ababaab"));
    }
}
