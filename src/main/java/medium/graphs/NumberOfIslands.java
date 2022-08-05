package medium.graphs;

public class NumberOfIslands {

    public void dfs(int i, int j, boolean[][] visited, char[][] grid, int m, int n) {
        if(grid[i][j] == '0')
            return;
        else if(i<0 || j<0 || i>m-1 || j>n-1)
            return;
        visited[i][j] = true;
        dfs(i+1,j,visited,grid,m,n);
        dfs( i-1,j,visited,grid,m,n);
        dfs(i,j-1,visited,grid,m,n);
        dfs(i,j+1,visited,grid,m,n);
    }


    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int islands = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i,j,visited,grid,m,n);
                    islands++;
                }
            }
        }
        return islands;
    }

}
