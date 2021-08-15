package easy.array;

import java.util.Arrays;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] mat = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = matrix[j][i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = mat[i][n - j - 1];
            }
        }

    }
}
