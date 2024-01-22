package lc75.slidingwindow;

public class MaximumAverageSubArrayI {
    public double findMaxAverage(int[] nums, int k) {
        int movingSum = 0;
        for(int i=0;i<k; i++) {
            movingSum += nums[i];
        }
        int maxSum = movingSum;
        int i = k;
        while(i<nums.length) {
            movingSum -= nums[i-k];
            movingSum += nums[i];
            maxSum = Integer.max(maxSum, movingSum);
            i++;
        }
        // This thing is critical.
        return maxSum/1.0/k;
    }
}
