package mediumcard.arraystrings;

public class LongestPalindromicSubString {

    // First medium problem accepted in one go
    // Not Optimal Solution
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return s;
        int max = Integer.MIN_VALUE;
        int r = 0, t = 0;
        for (int i = 0; i < s.length(); i++) {
            int p = max > 0 ? i + max + 1 : (i + 1); // Interesting Optimization
            for (int j = p; j < s.length() + 1; j++) {
                if (max == s.length())
                    return s;
                if (isPalindrome(s.substring(i, j))) {
                    max = Math.max(j - i, max);
                    r = i;
                    t = j;
                }
            }
        }
        return s.substring(r, t);
    }

    private static boolean isPalindrome(String str) {
        int len = str.length();
        int a = 0, b = 0;
        if (len % 2 == 0) {
            a = (len / 2);
            b = (len / 2) - 1;
        } else {
            a = (len / 2) + 1;
            b = (len / 2) - 1;
        }
        while (a < str.length() && b >= 0) {
            if (str.charAt(a) != str.charAt(b))
                return false;
            a++;
            b--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abba"));
    }
}
