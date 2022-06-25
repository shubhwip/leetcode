package medium.dp;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-falling-path-sum/
// Highly Thoughtful Solution at https://leetcode.com/problems/minimum-falling-path-sum/discuss/186666/C%2B%2BJava-4-lines-DP
public class MinFallingMatrixSum {
    int[][] memo;
    public int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        memo = new int[len][len];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // int min = Integer.MAX_VALUE;
        // for(int i=0;i<len;i++) {
        //    int result = minFallingSumMemo(matrix, 0, i, len);
        //    min = Math.min(min, result);
        // }
        // return min;
        return minFallingSumTab(matrix, len);
    }

    public int minFallingSumRec(int[][] matrix, int row, int col, int len) {
        if((row > len-1 || col > len-1) || (row < 0 || col < 0))
            return Integer.MAX_VALUE;
        int a = minFallingSumRec(matrix, row+1, col-1, len);
        int b = minFallingSumRec(matrix, row+1, col, len);
        int c = minFallingSumRec(matrix, row+1, col+1, len);
        int min = Math.min(Math.min(a, b), c);
        return min == Integer.MAX_VALUE ? matrix[row][col] : matrix[row][col] + Math.min(Math.min(a, b), c);
    }

    // Accepted in One Go
    // Felt Good
    public int minFallingSumMemo(int[][] matrix, int row, int col, int len) {
        if((row > len-1 || col > len-1) || (row < 0 || col < 0))
            return Integer.MAX_VALUE;
        if(memo[row][col] != -1)
            return memo[row][col];
        int a = minFallingSumMemo(matrix, row+1, col-1, len);
        int b = minFallingSumMemo(matrix, row+1, col, len);
        int c = minFallingSumMemo(matrix, row+1, col+1, len);
        int min = Math.min(Math.min(a, b), c);
        return memo[row][col] = (min == Integer.MAX_VALUE ? matrix[row][col] : matrix[row][col] + min);
    }

    public int minFallingSumTab(int[][] matrix, int len) {
        int[][] tab = new int[len+1][len+1];
        for(int[] row: tab) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for(int i=len-1;i>=0;i--) {
            for(int j=len-1;j>=0;j--) {
                int min = Integer.MAX_VALUE;
                if(j == 0) {
                    min = Math.min(Math.min(Integer.MAX_VALUE, tab[i+1][j]), tab[i+1][j+1]);
                } else {
                    min = Math.min(Math.min(tab[i+1][j-1], tab[i+1][j]), tab[i+1][j+1]);
                }
                tab[i][j] = (min == Integer.MAX_VALUE ? matrix[i][j] : matrix[i][j] + min);
            }
        }
        return tab[0][0];
    }

}
