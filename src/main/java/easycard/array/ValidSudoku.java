package easycard.array;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] hor = new boolean[10];
            boolean[] ver = new boolean[10];
            for (int j = 0; j < 9; j++) {

                if (board[i][j] != '.') {
                    int c = Integer.parseInt(String.valueOf(board[i][j]));
                    if (hor[c])
                        return false;
                    else
                        hor[c] = true;
                }

                if (board[j][i] != '.') {
                    int c = Integer.parseInt(String.valueOf(board[j][i]));
                    if (ver[c])
                        return false;
                    else
                        ver[c] = true;
                }

            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                boolean[] box = new boolean[10];
                for (int p = i; p < i + 3; p++) {
                    for (int q = j; q < j + 3; q++) {
                        if (board[p][q] != '.') {
                            int c = Integer.parseInt(String.valueOf(board[p][q]));
                            if (box[c])
                                return false;
                            box[c] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
