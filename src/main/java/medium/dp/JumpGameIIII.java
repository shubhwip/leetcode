package medium.dp;

import org.apache.commons.math3.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/jump-game-vi/discuss/1260736/Jump-Game-VI-or-Optimizations-from-Brute-Force-to-Dynamic-Programming-w-Explanation

public class JumpGameIIII {
    static int[] memo;
    public static int maxResult1(int[] nums, int k) {
        memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);
        return maxResultMemo(nums,k,0,nums.length);
    }

    public static int maxResult(int[] nums, int k) {
        Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>(k,
                Comparator.comparing(o -> -o.getValue()));
        pq.offer(new Pair<>(0, nums[0]));

        int max = nums[0], ans;
        for (int i = 1; i < nums.length; i++) {
            while (pq.peek().getKey() < i - k) { // We just compare the top node and see if it is outside of window or not.
                pq.poll();
            }

            max = nums[i] + pq.peek().getValue();
            pq.offer(new Pair<>(i, max));
        }

        return max;
    }


    public static int maxResultRecursive(int[] nums, int k, int initialPosition, int numsLen) {
        if(initialPosition >= numsLen)
            return 0;
        if(memo[initialPosition] != -1)
            return memo[initialPosition];

        int max = Integer.MIN_VALUE;

        for(int j=initialPosition+1;j<=(Math.min(numsLen, initialPosition+k));j++) {
            memo[initialPosition] = Math.max(max, maxResultRecursive(nums, k, j, numsLen) + nums[initialPosition]);
        }

        return memo[initialPosition];
    }

    // First Go Passed 29/71 Cases
    public static int maxResultMemo1(int[] nums, int k, int initialPosition, int numsLen) {
        if(initialPosition >= numsLen)
            return 0;
        if(memo[initialPosition] != Integer.MIN_VALUE)
            return memo[initialPosition];

        int max = Integer.MIN_VALUE;

        for(int j=initialPosition+1;j<=(Math.min(numsLen, initialPosition+k));j++) {
            max = Math.max(max, maxResultMemo(nums, k, j, numsLen) + nums[initialPosition]);
        }

        return memo[initialPosition] = max;
    }

    public static int maxResultMemo(int[] nums, int k, int initialPosition, int numsLen) {
        if(memo[initialPosition] != Integer.MIN_VALUE)
            return memo[initialPosition];

        int max = Integer.MIN_VALUE;

        for(int j=1;j<=k;j++) {
            if((initialPosition + j) < numsLen)
                memo[initialPosition] = Math.max(memo[initialPosition], maxResultMemo(nums, k, initialPosition+j, numsLen) + nums[initialPosition]);
        }

        return memo[initialPosition];
    }

    public static void main(String[] args) {
        System.out.println(maxResult(new int[]{1,-1,-2,4,-7,3}, 2));
    }
}
