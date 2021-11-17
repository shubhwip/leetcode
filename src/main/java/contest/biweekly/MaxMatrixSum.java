package contest.biweekly;

import java.util.Arrays;

public class MaxMatrixSum {

    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int[] data = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i * n + j] = matrix[i][j];
            }
        }
        Arrays.sort(data);
        for (int i = 0; i + 1 < n * n; i += 2) {
            if (data[i] + data[i + 1] < 0) {
                data[i] = -data[i];
                data[i + 1] = -data[i + 1];
            }
        }
        long sum = 0;
        for (int x : data) {
            sum += x;
        }
        return sum;
    }

    public long maxMatrixSum1(int[][] mat) {
        int n = mat.length;
        int min = 1000000;
        int par = 1;
        long tot = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tot += Math.abs(mat[i][j]);
                min = Math.min(min, Math.abs(mat[i][j]));
                if (mat[i][j] < 0)
                    par *= -1;
            }
        }
        //System.out.println(tot+" "+min);
        if (par == 1)
            return tot;
        else
            return tot - min * 2;
    }

    public static void main(String[] args) {

    }
}
