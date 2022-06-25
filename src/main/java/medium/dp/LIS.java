package medium.dp;

import java.util.Arrays;

// https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/810/discuss/1326552/Optimization-From-Brute-Force-to-Dynamic-Programming-Explained!
public class LIS {
    public static int[][] dp;
    public static int lengthOfLISTabulation(int[] nums) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        for(int[] row: dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for(int i=0;i<nums.length;i++) {
            for(int j=0;j<nums.length;j++) {
                if(i>j)
                    dp[i][j] = 0;
                else if(i==j-1) {
                    if(nums[j-1] < nums[j]) {
                        dp[i][j] = Math.max(2, dp[i][j]);
                    } else
                        dp[i][j] = 0;
                }
            }
        }
        for(int i=0;i<nums.length;i++) {
            for(int j=0;j<nums.length;j++) {
                if(dp[i][j] == Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][nums.length-1];
    }


    public static int lengthOfLIS(int[] nums) {
        dp = new int[nums.length+1][nums.length+1];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
        //return lengthOfLISRecursive(nums, 0 ,0);
        return lengthOfLISMemo(nums, 0 ,-1);
    }

    public static int lengthOfLISRecursive(int[] nums, int i, int prev) {
        if(i>= nums.length)
            return 0;
        int take = 0;
        int dontTake = lengthOfLISRecursive(nums, i+1, prev);
        if(nums[i] > prev) {
            take = 1 + lengthOfLISRecursive(nums, i+1, nums[i]);
        }
        return Integer.max(take, dontTake);
    }
    public static int lengthOfLISMemo(int[] nums, int i, int prevI) {
        if(i>= nums.length)
            return 0;
        if(dp[i][prevI+1] != -1)
            return dp[i][prevI+1];
        int take = 0;
        int dontTake = lengthOfLISMemo(nums, i+1, prevI);
        if(prevI == -1 || nums[i] > nums[prevI]) {
            take = 1 + lengthOfLISMemo(nums, i+1, i);
        }
        return dp[i][prevI+1] = Integer.max(take, dontTake);
    }


    public static void main(String[] args) {
        //System.out.println(lengthOfLIS(new int[]{0,3,1}));
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
