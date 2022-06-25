package easy.slidingwindow;

public class MaxSubArraySum {
    public static double findMaxAverage(int[] nums, int k) {
        if(k>nums.length)
            return 0.0;
        return findMaxAverageBF(nums, k);
    }

    // WA
    // https://leetcode.com/submissions/detail/721121588/
    // TLE
    public static double findMaxAverageBF(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length-k+1;i++) {
            double sum = 0.0;
            for(int j=i;j<i+k;j++) {
                sum += nums[j];
            }
            max = Math.max(max, sum/k);
        }
        return max;
    }

    // Accepted
    public double findMaxAverageSlidingWindow(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        int l=0, r=0;
        double sum = 0.0;
        while(r < nums.length) {
            if(r-l == k) {
                max = Math.max(max, sum/k);
                sum = sum - nums[l];
                l++;
            }
            sum += nums[r];
            r++;
        }
        if(r-l == k) {
            max = Math.max(max, sum/k);
        }
        return max;
    }

    // Similar Problem
    // https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int l=0, r=0;
        int subs = 0;
        double sum = 0.0;
        while(r<arr.length) {
            if(r-l == k) {
                if((sum/k) >= Double.valueOf(threshold)) {
                    subs++;
                }
                sum = sum - arr[l];
                l++;
            }
            sum += arr[r];
            r++;
        }
        if(r-l == k) {
            if((sum/k) >= Double.valueOf(threshold)) {
                subs++;
            }
        }
        return subs;
    }

    public static void main(String[] args) {
        //System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 3));
        System.out.println(numOfSubarrays(new int[]{2,2,2,2,5,5,5,8}, 3, 4));
    }
}
