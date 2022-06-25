package medium.sortingsearching;

public class Search2DMatrix {
    public static boolean searchRow(int[][] matrix, int target, int p, int r, int c) {
        System.out.println("Row" + "," + p + "," + r + "," + c + "," );
        if(p<c-1)
            return false;
        else if(p == c-1 && target == matrix[p][p])
            return true; 
        int i = p;
        int j = r;
        while(i<j) {
            int mid = (i+j)/2;
            System.out.println("Row" + i + "," + j + "," + p + "," + r + "," + c + "," + mid);
            if(target == matrix[p][mid])
                return true;
            else if(target > matrix[p][mid])
                i = mid+1;
            else 
                j = mid-1;
        }
        return false;
    }
    
    public static boolean searchColumn(int[][] matrix, int target, int p, int r, int c) {
        if(p>r-1)
            return false;
        else if(p == r-1 && target == matrix[p][p])
            return true;
        int i = c;
        int j = p;
        while(j<i) {
            int mid = (i+j)/2;
            System.out.println(i + "," + j + "," + p + "," + r + "," + c + "," + mid);
            if(target == matrix[mid][p])
                return true;
            else if(target > matrix[mid][p])
                j = mid+1;
            else 
                i = mid-1;
        }
        return false;
    }
    

    public static boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;
        int m = 0;
        if(r > c) 
            m = c-1;
        else if(r < c)
            m = r-1;
        // 43/129 passed in first run
        // Test case failed Input:
        // [[-5]]
        // -5
        else if( r==c) {
            if(r == 1 && target == matrix[0][0])
                return true;
            else if(r == 1 && target != matrix[0][0])
                return false;
            else
                m = r-1;
            
        }
        for(int i=0;i<=m;i++) {
            boolean isPresentInRow = searchRow(matrix, target, i, r, c);
            boolean isPresentInCol = searchColumn(matrix, target, i, r, c);
            if(isPresentInRow || isPresentInCol)
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] arr = new int[][]{
            {1,2},
            {3,4},
            {5,6}
        };
        System.out.println(searchMatrix(arr, 6));
        
    }
}
