package medium.sortingsearching;

// https://leetcode.com/problems/search-a-2d-matrix-ii/
// Four Approaches
// Brute Force Search in entire matrix O(mxn)
// Binary Search in rows O(mlogn)
// Binary Search Optimized O(m+n) https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/642522/Java-Solution-with-Explaination-Faster-than-100
// Divide And Conquer https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66147/*Java*-an-easy-to-understand-divide-and-conquer-method
public class Search2DMatrixII {
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

    public boolean searchMatrixMostOptimized(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    // Solved 2nd time with some Mistakes
    // Mistake 1 : Binary Search Algorithm : had to correct multiple times
    // Mistake 2 : Putting Semicolon at the end of loop
    public static boolean search(int[][] matrix, int target, int i, int m, int n) {
        int low = 0;
        int high = n-1;
        while(low <= high) {
            int mid = (low+high)/2;
            if(matrix[i][mid] == target) {
                return true;
            }
            else if(matrix[i][mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return false;
    }



    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(search(matrix, target, 0, m, n))
            return true;
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
