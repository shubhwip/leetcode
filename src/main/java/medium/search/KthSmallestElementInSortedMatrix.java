package medium.search;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1685550/Here's-why-Binary-Search-works.-(Explain-it-to-me-like-I'm-5)
public class KthSmallestElementInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if(k == 1) {
            return matrix[0][0];
        }
        else if(k == n*n) {
            return matrix[n-1][n-1];
        }
        int left = matrix[0][0];
        int right = matrix[n-1][n-1] + 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int count = 0,  j = n - 1;
            for(int i = 0; i < n; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

}
