package medium.dp;

public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return getUniquePathsDPMemoization(m,n,0,0, dp);
    }

    //Recursive Solution
    //37 / 63 test cases passed.
    //Status: Time Limit Exceeded
    //Submitted: 0 minutes ago
    //Last executed input:
    //23
    //12
    // Recursive
    public static int getUniquePathsRecursive(int m, int n, int i, int j) {
        if(i == m && j == n){
            return 1;
        }
        else if(i > m || j > n) {
            return 0;
        }
        return getUniquePathsRecursive(m,n,i,j+1) + getUniquePathsRecursive(m,n,i+1,j);
    }

    // Memoization
    public static int getUniquePathsDPMemoization(int m, int n, int i, int j, int[][] dp) {
        if(i > m-1 || j > n-1) {
            return 0;
        }
        if(dp[i][j] > 0)
            return dp[i][j];
        else if(i == m-1 && j == n-1){
            return 1;
        }

        int left = getUniquePathsDPMemoization(m,n,i,j+1, dp);
        int right = getUniquePathsDPMemoization(m,n,i+1,j, dp);

        dp[i][j] = left + right;
        return dp[i][j];
    }

    public static int getUniquePathsDPTabulation(int m, int n, int i, int j) {
        int[][] dp = new int[m][n];
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(23,12));
        System.out.println(uniquePaths(2,2));
    }
}
