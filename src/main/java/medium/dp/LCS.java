package medium.dp;

// Similar to LCS with Minor Tweaks ( https://leetcode.com/problems/longest-common-subsequence/ )
// https://leetcode.com/problems/delete-operation-for-two-strings/
public class LCS {

    public static int lcs(String word1, String word2, int i, int j) {
        if(i==word1.length() || j==word2.length())
            return 0;
        else if(word1.charAt(i) == word2.charAt(j))
            return 1 + lcs(word1, word2, i+1, j+1);
        else
            return Math.max(lcs(word1, word2, i, j+1), lcs(word1, word2, i+1,j));
    }

    public static int lcsMemo(String word1, String word2, int i, int j, int[][] dp) {
        if(i == word1.length() || j == word2.length())
            return 0;
        else if(word1.charAt(i) == word2.charAt(j))
            dp[i][j] = 1 + lcsMemo(word1, word2, i+1, j+1, dp);
        if(dp[i][j] != -1)
            return dp[i][j];
        else
            return dp[i][j] = Math.max(lcsMemo(word1, word2, i, j+1, dp), lcsMemo(word1, word2, i+1,j, dp));
    }

    public static int longestCommonSubsequenceTabulation(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public int longestCommonSubsequenceOptimized(String text1, String text2) {
        int[] prev = new int[text1.length()+1];
        int[] cur = new int[text1.length()+1];
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        for(int j = text2.length() - 1; j >= 0; j--){
            for(int i = text1.length() - 1; i >=0; i--){
                if(t1[i] == t2[j])
                    cur[i] = 1 + prev[i+1];
                else
                    cur[i] = Math.max(prev[i],cur[i+1]);
            }
            int[] temp = prev;
            prev = cur;
            cur = temp;
        }
        return prev[0];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequenceTabulation("10101", "10101"));
    }
}
