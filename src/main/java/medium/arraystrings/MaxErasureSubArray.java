package medium.arraystrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/maximum-erasure-value/discuss/2140577/An-Interesting-Optimisation-or-JAVA-Explanation
public class MaxErasureSubArray {

    public static int maximumUniqueSubarray(int[] nums) {
        return maxSubSum(nums);
    }

    // Brute Force
    // 54 / 62 test cases passed.
    public static int maxSubSum1(int[] nums) {
        int numsSize = nums.length;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<numsSize;i++) {
            int sum = nums[i];
            Set<Integer> s = new HashSet<>();
            s.add(nums[i]);
            for(int j=i+1;j<numsSize;j++) {
                if(s.contains(Integer.valueOf(nums[j])))
                    break;
                else {
                    s.add(nums[j]);
                    sum = sum + nums[j];
                    max = Integer.max(sum, max);
                }
            }
            s.clear();
        }
        return max;
    }

    // Still Brute Force
    // 58 / 62 test cases passed.
    public static int maxSubSum(int[] nums) {
        int numsSize = nums.length;
        int max = nums[0];
        int p = 0;
        int sum = 0;
        Set<Integer> s = new HashSet<>();
        for(int i=p;i<numsSize;i++) {
                if(s.contains(Integer.valueOf(nums[i]))) {
                    p = p+1;
                    i = p-1;
                    sum =0;
                    s.clear();
                }
                else {
                    s.add(nums[i]);
                    sum = sum + nums[i];
                    max = Integer.max(sum, max);
                }
        }
        return max;
    }

    // Slding window
    public int maximumUniqueSubarray3(int[] nums) {
        int maxScore = 0, currScore = 0;
        Set<Integer> set = new HashSet<>();

        for (int l=0, r=0; r<nums.length; r++) {
            while (!set.add(nums[r])) {
                currScore -= nums[l];
                set.remove(nums[l++]);
            }
            currScore += nums[r];
            maxScore = Math.max(maxScore, currScore);
        }

        return maxScore;
    }

    // Prefix Sum and Sliding Window
    public int maximumUniqueSubarray2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] cost = new int[nums.length];
        int left = 0, right = 0, maxSum = 0, currSum = 0;
        cost[0] = nums[0];

        while(right<nums.length) {
            int num = nums[right];
            if(right > 0 && right < nums.length) {
                cost[right] = nums[right] + cost[right-1];
            }
            if(map.containsKey(num)) {
                currSum -= cost[map.get(num)];
                left = Math.max(left, map.get(num)+1);
            }
            currSum += nums[right];
            map.put(num, right++);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        //System.out.println(maximumUniqueSubarray(new int[]{5,2,1,2,5,2,1,2,5}));
        System.out.println(maximumUniqueSubarray(new int[]{4,2,4,5,6}));
    }
}
