package medium.slidingwindow;

public class MinSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int l=0, r=0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while(r<nums.length) {
            while(sum >= target) {
                min = Math.min(min, r-l);
                sum -= nums[l];
                l++;
            }
            sum += nums[r];
            r++;
        }
        while(sum >= target) {
            if(sum >= target)
                min = Math.min(min, r-l);
            sum -= nums[l];
            l++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }
}
