package medium.dp;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/triangle/
public class Triangle {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int memo[][] = new int[triangle.size()][triangle.size()];
        //return minPathSum(triangle, 0, 0);
        //return minPathSumIter(triangle);
        for(int[] m: memo) {
            Arrays.fill(m, Integer.MAX_VALUE);
        }
        return minPathSumMemo(triangle, 0 ,0 , memo);
    }

    // i is index of outer list in triangle
    // j is index of inner list in triangle
    // Recursive
    // TLE
    public static int minPathSum(List<List<Integer>> triangle, int i, int j) {
        // Base case
        if(j > triangle.get(i).size())
            return 0;
        if(i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        return triangle.get(i).get(j) + Math.min(minPathSum(triangle, i+1, j), minPathSum(triangle, i+1, j+1));
    }

    // Memoization
    // TLE
    public static int minPathSumMemo(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        // Base case
        if(j > triangle.get(i).size())
            return 0;
        if(i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        if(dp[i][j] != Integer.MAX_VALUE)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        dp[i][j] = triangle.get(i).get(j) + Math.min(minPathSumMemo(triangle, i+1, j, dp), minPathSumMemo(triangle, i+1, j+1, dp));
        return Math.min(min, dp[i][j]);
    }

    // Memoization
    public int minPathSumMemo2(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        if(i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        if(dp[i][j] != Integer.MAX_VALUE)
            return dp[i][j];

        int first = minPathSumMemo(triangle, i+1, j, dp);
        int second = minPathSumMemo(triangle, i+1, j+1, dp);
        int res = triangle.get(i).get(j) + Integer.min(first, second);
        dp[i][j] = res;
        return res;
    }

    // Tabulation
    // Works fine
    public static int minPathSumIter(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] rows = new int[size][size];
        rows[0][0] = triangle.get(0).get(0);
        rows[1][0] = rows[0][0] + triangle.get(1).get(0);
        rows[1][1] = rows[0][0] + triangle.get(1).get(1);
        for(int i=2;i<size;i++) {
            for(int j=0;j<triangle.get(i).size();j++) {
                if(j == 0)
                    rows[i][j] = rows[i-1][j] + triangle.get(i).get(j);
                else if(j == triangle.get(i).size()-1)
                    rows[i][j] = rows[i-1][j-1] + triangle.get(i).get(j);
                else
                    rows[i][j] = triangle.get(i).get(j) + Math.min(rows[i-1][j-1], rows[i-1][j]);
            }
        }
        int min = Integer.MAX_VALUE;
        for(int j=0;j<size;j++) {
            min = Math.min(min, rows[size-1][j]);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minimumTotal(Arrays.asList(Arrays.asList(2),Arrays.asList(3,4),Arrays.asList(6,5,1),Arrays.asList(4,1,8,3) )));
    }
}
