package medium.sortingsearching;

import java.util.Random;

public class PeakElement {

    // Memory Optimized Solution
    // Iterative Solution
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[mid + 1])
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    // Recursive Binary Search
    public static int findPeakElementRecur(int[] nums) {
        if (nums.length == 1)
            return 0;
        return findPeak(nums, 0, nums.length - 1);
    }

    public static int findPeak(int[] nums, int i, int j) {
        int mid = (i + j) / 2;
        if (i >= j) {
            return -1;
        } else if (mid >= 1 && mid < nums.length - 1 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
            return mid;
        int a = findPeak(nums, i, mid);
        int b = -1;
        if (a == -1)
            b = findPeak(nums, mid + 1, j);
        else
            return a;
        if (i == 0 && nums[i] > nums[i + 1])
            return i;
        else if (j == nums.length - 1 && nums[j] > nums[j - 1]) {
            return j;
        }
        return b;
    }

    // Completely Wrong Approach
    // Can be stuck into infinite processing
    public static int findPeakElementRandom(int[] nums) {
        // Pick an element randomly and check.
        boolean peak = false;
        boolean b[] = new boolean[nums.length];
        Random r = new Random();
        int res = -1;
        while (!peak) {
            int rand = r.nextInt(nums.length - 2) + 1;
            if (!b[rand] && nums[rand - 1] < nums[rand] && nums[rand] > nums[rand + 1]) {
                peak = true;
                res = rand;
            }
            b[rand] = true;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(findPeakElement(new int[]{1, 2, 3, 4}));
        System.out.println(findPeakElement(new int[]{5, 4, 3, 2, 1}));
        System.out.println(findPeakElement(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));
        System.out.println(findPeakElement(new int[]{1, 2, 1, 2, 1, 2, 1}));
        System.out.println(findPeakElement(new int[]{2, 1, 2, 1, 2, 1, 2}));
    }
}
