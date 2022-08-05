package medium.graphs;

public class MaxAreaOfIsland {
    int dfs1(int[][] grid, int i, int j, boolean[][] visited, int m, int n) {
        if(i>m-1||j>n-1||i<0||j<0)
            return 0;
        if(grid[i][j] == 1 && !visited[i][j]) {
            visited[i][j] = true;
            return 1 + dfs1(grid, i, j+1,visited,m,n) + dfs1(grid, i+1, j,visited,m,n) + dfs1(grid, i, j-1,visited,m,n) + dfs1(grid, i-1, j,visited,m,n);
        }
        else
            return 0;
    }

    int iarea(int[][] grid, int i, int j, int m, int n) {
        if(i<m && j<n && i>=0 &&  j>=0 && grid[i][j]==1){
            grid[i][j]=0;
            int up=iarea(grid,i-1,j,m,n);
            int down=iarea(grid, i+1,j,m,n);
            int left=iarea(grid, i,j-1,m,n);
            int right=iarea(grid,i,j+1,m,n);
            return 1+left+right+up+down;
        }
        return 0;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //boolean[][] visited = new boolean[m][n];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 1) {
                    max = Math.max(iarea(grid,i,j,m,n), max);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
