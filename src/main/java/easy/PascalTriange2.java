package easy;

import java.util.ArrayList;
import java.util.List;

public class PascalTriange2 {

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> l = new ArrayList<>();
        int[][] mat = new int[rowIndex + 1][rowIndex + 1];
        getData(rowIndex, mat, rowIndex, rowIndex);
        for (int i = 0; i <= rowIndex; i++) {
            l.add(mat[rowIndex][i]);
        }
        return l;
    }

    public static void getData(int rowIndex, int[][] mat, int p, int q) {
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= rowIndex; j++) {
                if (i == j || j == 0)
                    mat[i][j] = 1;
                else if (j > i)
                    mat[i][j] = 0;
                else
                    mat[i][j] = mat[i - 1][j - 1] + mat[i - 1][j];
            }
        }
//        }
//        if (i == j || j == 0)
//            return 1;
//        return getData(rowIndex, mat, i - 1, j - 1) + getData(rowIndex, mat, i - 1, j);
    }

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }
}
