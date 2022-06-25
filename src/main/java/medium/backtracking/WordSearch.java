package medium.backtracking;

public class WordSearch {

    // Copied Solution for understanding
    static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static boolean exist1(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backTrack1(board, i, j, m, n, word, 0, visited)) return true;
            }
        }
        return false;
    }

    private static boolean backTrack1(char[][] board, int row, int col, int m, int n, String word, int idx, boolean[][] visited) {
        if (idx >= word.length()) return true;
        if (outOfRange(row, col, m, n) || board[row][col] != word.charAt(idx) || visited[row][col]) return false;
        visited[row][col] = true;
        for (int i = 0; i < dirs.length; i++) {
            if (backTrack1(board, row + dirs[i][0], col + dirs[i][1], m, n, word, idx + 1, visited)) {
                return true;
            }
        }
        visited[row][col] = false;
        return false;
    }

    private static boolean outOfRange(int row, int col, int m, int n) {
        return row < 0 || col < 0 || row >= m || col >= n;
    }
    // Copied Solution


    static StringBuilder result = new StringBuilder();

    // 40 / 57 test cases passed. Solution=> Introduced Visited Array
    // 49 / 57 test cases passed. Solution => Initialize visited array each time first character found
    // 54/57 test cases passed.
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean visited[][];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited = new boolean[m][n];
                    StringBuilder sb = new StringBuilder();
                    backtrack(i, j, 0, word, sb.toString(), board, m, n, visited);
                    if (result.toString().equals(word))
                        return true;
                }
            }
        }
        return false;
    }

    private static void backtrack(int i, int j, int c, String word, String sb, char[][] board, int m, int n, boolean[][] visited) {
        if (sb.equals(word) && !result.toString().equals(word)) {
            result.append(sb);
        }
        if (c >= word.length())
            return;
        if (!isValid(i, j, m, n))
            return;
        if (sb.length() > word.length())
            return;
        if (board[i][j] != word.charAt(c)) {
            //visited[i][j] = true;
            return;
        } else if (!visited[i][j] && board[i][j] == word.charAt(c)) {
            visited[i][j] = true;
            StringBuilder sb1 = new StringBuilder(sb).append(board[i][j]);
            c = c + 1;
            backtrack(i - 1, j, c, word, sb1.toString(), board, m, n, visited);
            backtrack(i, j - 1, c, word, sb1.toString(), board, m, n, visited);
            backtrack(i, j + 1, c, word, sb1.toString(), board, m, n, visited);
            backtrack(i + 1, j, c, word, sb1.toString(), board, m, n, visited);
        }
        visited[i][j] = false;

    }

    private static boolean isValid(int x, int y, int r, int s) {
        return (x < 0 || y < 0 || x > r - 1 || y > s - 1) ? false : true;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'C', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCDE"));

        // 40/57
        System.out.println(exist(board, "ABCB"));

        // 49/57
        char[][] board1 = {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        System.out.println(exist(board1, "AAB"));

        // 54/57 test cases passed
        char[][] board2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board2, "ABCESEEEFS"));
    }
}
