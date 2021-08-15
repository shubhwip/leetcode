package easy;

public class HouseRobberDp {

    public int rob(int[] nums) {
        return robr(nums, 0);
    }

    public int robr(int[] nums, int p) {
        if (p == nums.length - 1 || p == nums.length - 2)
            return nums[p];
        return Integer.max(nums[p] + robr(nums, p + 2), nums[p + 1] + robr(nums, p + 3));
    }

    // Wrong Approach
    public int robrDp(int[] nums) {
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i += 2) {
            dp[i] = nums[i] + dp[i - 2];
        }
        for (int j = 3; j < nums.length; j += 2) {
            dp[j] = nums[j] + dp[j - 2];
        }
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }

    public int robrDp2(int[] nums) {
        if (nums.length == 0)
            return 0;
        else if (nums.length == 1)
            return nums[0];
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        int max = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length; i++) {
            if ((i - 3) >= 0 && (i - 2) >= 0)
                dp[i] = nums[i] + ((dp[i - 2] > 0 && dp[i - 3] > 0) ? Integer.max(dp[i - 2], dp[i - 3]) : 0);
            else if ((i - 2) >= 0)
                dp[i] = nums[i] + ((dp[i - 2] > 0) ? dp[i - 2] : 0);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        HouseRobberDp dp = new HouseRobberDp();
        System.out.println(dp.robrDp2(new int[]{1, 2, 3, 1}));
        System.out.println(dp.robrDp2(new int[]{2, 7, 9, 3, 1}));
        System.out.println(dp.robrDp2(new int[]{5, 2, 3, 8}));
        System.out.println(dp.robrDp2(new int[]{1,2}));
    }
}
