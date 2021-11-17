package mediumcard.treegraphs;

import javafx.util.Pair;

import java.util.Stack;

public class NumberOfIslands {

    // 49/49 test cases passed but very slower solution
    public static int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        boolean visited[][] = new boolean[r][c];
        int islands = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    visited[i][j] = true;
                    Stack<Pair<Integer, Integer>> s = new Stack<>();
                    s.push(new Pair<>(i, j));
                    visited[i][j] = true;
                    while (!s.isEmpty()) {
                        Pair<Integer, Integer> p = s.pop();
                        int x = p.getKey();
                        int y = p.getValue();
                        if (isValid(x - 1, y, r, c)) {
                            visited[x - 1][y] = true;
                            if (grid[x - 1][y] == '1') {
                                s.push(new Pair<>(x - 1, y));
                            }
                        }
                        if (isValid(x, y - 1, r, c) && !visited[x][y - 1]) {
                            visited[x][y - 1] = true;
                            if (grid[x][y - 1] == '1') {
                                s.push(new Pair<>(x, y - 1));
                            }
                        }
                        if (isValid(x, y + 1, r, c) && !visited[x][y + 1]) {
                            visited[x][y + 1] = true;
                            if (grid[x][y + 1] == '1') {
                                s.push(new Pair<>(x, y + 1));
                            }
                        }
                        if (isValid(x + 1, y, r, c) && !visited[x + 1][y]) {
                            visited[x + 1][y] = true;
                            if (grid[x + 1][y] == '1') {
                                s.push(new Pair<>(x + 1, y));
                            }
                        }
                    }
                    islands++;
                } else {
                    visited[i][j] = true;
                }
            }
        }
        return islands;
    }

    private static boolean isValid(int x, int y, int r, int s) {
        return (x < 0 || y < 0 || x > r - 1 || y > s - 1) ? false : true;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        char[][] grid2 = {{'1', '1', '1', '1', '1'}, {'1', '0', '1', '0', '1'}, {'1', '1', '1', '1', '1'}};

        System.out.println(numIslands(grid2));
    }
}
