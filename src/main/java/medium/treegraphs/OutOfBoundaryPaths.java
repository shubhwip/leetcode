package medium.treegraphs;

import java.util.Arrays;

public class OutOfBoundaryPaths {

    // 76 / 94 test cases passed.
    // TLE
    // Working Solution
    // Calculating SubProblems Again and Again
    public static int dfs(int m, int n, int r, int c, int l, int moves) {
        if((r<0 || c<0 || r>m-1 || c > n-1)) {
            if (l + 1 == moves)
                return 1;
            else
                return 0;
        }
        for(int z=moves;z<=l;z++) {
            int p = dfs(m, n, r+1, c, l, moves+1);
            int q = dfs(m, n, r, c+1, l, moves+1);
            int g = dfs(m, n, r-1, c, l, moves+1);
            int h = dfs(m, n, r, c-1, l, moves+1);
            return p+q+g+h;
        }
        return 0;
    }

    public static int findPaths1(int m, int n, int maxMove, int startRow, int startColumn){
        int paths = 0;
        for(int i=1;i<=maxMove;i++) {
            paths += dfs(m, n, startRow, startColumn, i, 1);
        }
        return paths;
    }

    static int[][][] memo;
    static int[][] possibleMoves = {{0,1}, {1,0},{-1,0},{0,-1}};
    static int mod = 1000000000 + 7;

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn){
        memo = new int[m][n][maxMove+1];
        for(int[][] row : memo) {
            for(int[] r : row) {
                Arrays.fill(r, -1);
            }
        }
        return (int) (depth(m, n, maxMove, startRow, startColumn)%mod);
    }

    public static int depth(int m, int n, int maxMove, int r, int c){
        if((r<0 || c<0 || r>m-1 || c > n-1)) {
                return 1;
        }
        if(maxMove == 0)
            return 0;
        if(memo[r][c][maxMove] != -1)
            return memo[r][c][maxMove];

        memo[r][c][maxMove] = 0;

        for(int i=0;i<4;i++) {
            int x = r + possibleMoves[i][0];
            int y = c + possibleMoves[i][1];
            memo[r][c][maxMove] = (memo[r][c][maxMove] + depth(m,n,maxMove-1,x,y) % mod) % mod;
        }

        return memo[r][c][maxMove];

    }

    public static void main(String[] args) {
        System.out.println(findPaths(2,2,2,0,0));
    }
}
