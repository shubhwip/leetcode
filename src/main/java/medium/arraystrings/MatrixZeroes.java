package medium.arraystrings;

import java.util.ArrayList;
import java.util.List;

public class MatrixZeroes {
    // One optimization can be on the basis of number of zeroes found. If number of zeroes are greater than equal to m or n then set all members to zero
    // Not space efficient
    // Correct Solution and O(m*n) solution
//    164 / 164 test cases passed.
//            Status: Accepted
//    Runtime: 1 ms
//    Memory Usage: 40.6 MB
    public static void setZeroes(int[][] matrix) {
        List<List<Integer>> ls = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    ls.add(l);
                    cnt++;
                }
            }
        }
        if (cnt > 0) {
            for (int m = 0; m < ls.size(); m++) {
                int i = ls.get(m).get(0);
                int j = ls.get(m).get(1);
                int p = 0;
                while (p < matrix[0].length) {
                    matrix[i][p] = 0;
                    p++;
                }
                int q = 0;
                while (q < matrix.length) {
                    matrix[q][j] = 0;
                    q++;
                }
            }
        }
    }
//        Your input
//    [[0,1,2,0],[3,4,-1,-1],[1,3,1,5]]
//        Your answer
//    [[0,0,0,0],[0,4,0,0],[0,3,1,0]]
//        Expected answer
//    [[0,0,0,0],[0,4,-1,0],[0,3,1,0]]

    // Report this in Discuss Chat

    // ALl test cases passed but implementation wrong
//    164 / 164 test cases passed.
//            Status: Accepted
//    Runtime: 3 ms
//    Memory Usage: 50.9 MB
    // Not Optimized Solution for Memory, O(m*n(m+n))
    // Space efficient Algorithm
    // Not completely correct solution
    public static void setZeroesA(int[][] matrix) {
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    cnt++;
                    int p = 0;
                    while (p < matrix[0].length) {
                        matrix[i][p] = (matrix[i][p] == 0 ? 0 : -1);
                        p++;
                    }
                    int q = 0;
                    while (q < matrix.length) {
                        matrix[q][j] = (matrix[q][j] == 0 ? 0 : -1);
                        q++;
                    }
                }
            }
        }

        if (cnt > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == -1) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}
        };

//        int[][] a = new int[][]{
//                {-1}, {97}, {-1}
//        };
        setZeroes(a);
    }
}
