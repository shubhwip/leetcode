package hard.matrix;

import java.util.Arrays;

public class NumberOfMatricesThatSumToTarget {
    public static boolean findTargetMatrix1(int[][] matrix, int a, int b, int p, int q, int target) {
        int sum = 0;
        for(int i=a;i<a+p;i++) {
            for(int j=b;j<b+q;j++) {
                sum += matrix[i][j];
            }
        }
        if(sum == target)
            return true;
        return false;
    }
    // Complexity very high
    // Naive Solution O((m*n)^3)
    // 35/40 Test Cases Passed
    public static int numSubmatrixSumTarget1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int count = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                for(int p=1;p<=m-i;p++) {
                    for(int q=1;q<=n-j;q++) {
                        if(findTargetMatrix1(matrix, i, j, p, q, target))
                            count++;
                    }
                }
            }
        }
        return count;
    }

    public static int findTargetMatrix2(int[][] matrix, int a, int b, int p, int q, int target) {
        int sum = 0;
        for(int i=a;i<a+p;i++) {
            for(int j=b;j<b+q;j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    // My Buggy Solution
    public static int numSubmatrixSumTarget2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int[] r: dp) {
            Arrays.fill(r, Integer.MIN_VALUE);
        }
        dp[0][0] = matrix[0][0];
        int count = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                for(int p=1;p<=m-i;p++) {
                    for(int q=1;q<=n-j;q++) {
                        if(p==1 && q==1 && matrix[i][j] == target) {
                                count++;
                                continue;
                        }
                        else if(p>1 && q>1) {
                            dp[p-1][q-1] = matrix[i+p-1][j+q-1] + dp[i+p-2][j+q-1] + dp[i+p-1][j+q-2] - dp[i+p-2][j+q-2];
                        } else {
                            dp[p-1][q-1] = findTargetMatrix2(matrix, i, j, p, q, target);
                        }
                        if(dp[p-1][q-1] != Integer.MIN_VALUE && dp[p-1][q-1] == target) {
                            System.out.println("for p " + p + " for q " + q + " for i " + i + " for j " + j);
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    // Copied DP Solution
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int count = 0;
        dp[1][1] = matrix[0][0];
        for (int i = 2; i <= m; i++) {
            dp[i][1] = dp[i - 1][1] + matrix[i - 1][0];
        }
        for (int j = 2; j <= n; j++) {
            dp[1][j] = dp[1][j - 1] + matrix[0][j - 1];
        }
        for (int i = 2; i <= m; i++) {
            int sum = matrix[i - 1][0];
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + sum + matrix[i - 1][j - 1];
                sum += matrix[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int x = i; x <= m; x++) {
                    for (int y = j; y <= n; y++) {
                        int sum = dp[x][y] + dp[i - 1][j - 1] - dp[i - 1][y] - dp[x][j - 1];
                        if (sum == target) {
                            count += 1;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        //System.out.println(numSubmatrixSumTarget(new int[][]{{1,-1, 1},{-1,1,-1},{1,-1,1}}, 0));
        System.out.println(numSubmatrixSumTarget(new int[][]{{1,0, 0},{0,1,0},{1,0,1}}, 0));
    }
}
