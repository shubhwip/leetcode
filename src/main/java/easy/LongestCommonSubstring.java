package easy;

public class LongestCommonSubstring {

    // Recursive Longest Common Substring
    public static int longestCommonSubstring(String X, String Y, int a, int b, int res) {
        if (a == 0 || b == 0)
            return res;
        if (X.charAt(a - 1) == Y.charAt(b - 1))
            return longestCommonSubstring(X, Y, a - 1, b - 1, res + 1);
        else
            return Integer.max(longestCommonSubstring(X, Y, a, b - 1, 0), longestCommonSubstring(X, Y, a - 1, b, 0));
    }

    // Recursive Longest Common Subsequence
    public static int longestCommonSubsequence(String X, String Y, int a, int b, int res) {
        if (a == 0 || b == 0)
            return res;
        if (X.charAt(a - 1) == Y.charAt(b - 1))
            return longestCommonSubsequence(X, Y, a - 1, b - 1, res + 1);
        else
            return Integer.max(longestCommonSubsequence(X, Y, a, b - 1, 0), longestCommonSubsequence(X, Y, a - 1, b, 0));
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABC", "AGBDCD", 3, 6, 0));
        System.out.println(longestCommonSubstring("ABC", "DAB", 3, 3, 0));
        System.out.println(longestCommonSubstring("ABCDEFGH", "ABCDEFGHO", 8, 9, 0));
    }
}
