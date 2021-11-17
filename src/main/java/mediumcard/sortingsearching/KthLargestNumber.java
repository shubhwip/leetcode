package mediumcard.sortingsearching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KthLargestNumber {


    public static void mergeSort(int[] A, int i, int j, int k) {
        if (i >= j)
            return;
        else {
            int mid = (i + j) / 2;
            mergeSort(A, i, mid,k);
            mergeSort(A, mid + 1, j,k);
            merge(A, i, j);
        }
    }

    public static void merge(int[] A, int i, int j) {
        int B[] = new int[i + j+1];
        int start = i;
        int mid = (i + j) / 2;
        int k = mid + 1;
        int l = i;
        while (i <= mid && k <= j) {
            if (A[i] > A[k]) {
                B[l++] = A[i++];
            } else {
                B[l++] = A[k++];
            }
        }
        if (i > mid) {
            for (; k <= j; ) {
                B[l++] = A[k++];
            }
        } else if (k > j) {
            for (; i <= mid; ) {
                B[l++] = A[i++];
            }
        }

        for (int z = start; z <= j; z++) {
            A[z] = B[z];
        }
    }

    // Worst Solution with merge sort
// 32 / 32 test cases passed.
//Status: Accepted
//Runtime: 198 ms
//Memory Usage: 187.2 MB
    public static int findKthLargestMergeSort(int[] nums, int k) {
        if (k > nums.length)
            return 0;
        mergeSort(nums, 0, nums.length - 1,k);
        return nums[k];
    }


    // Most Naive Solution
    // 6ms and 39 mb
    // Another solution could be QuickSelect Algorithm.
    // You are here!
    //Your runtime beats 44.88 % of java submissions.
    // You are here!
    //Your memory usage beats 90.16 % of java submissions
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length)
            return 0;
        List<Integer> set = new ArrayList<>();
        for (int num : nums) {
            set.add(num);
        }
        //mergeSort(nums, 0, nums.length-1);
        Collections.sort(set);
        int p = 0;
        int z = nums.length - k + 1;
        for (int num : set) {
            p++;
            if (p < z)
                continue;
            if (p == (z))
                return num;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(findKthLargestMergeSort(new int[]{5, 3, 2, 3, 4,  4, 6, 1}, 3));
    }
}
