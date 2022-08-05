package medium.dp;

public class WiggleSubsequence {
    // Naive Solution
    // Pick one index and check for rest of all elements and see if there is a subsequence.

    // Recursive Solution
    // Relation : Math.max( 1 + solve(i+1, true), solve(i+1, false)
    public static int wiggleMaxLength(int[] nums) {
        if(nums.length <= 2)
            return nums.length;
        return wiggleRec(nums, 0, nums[0])+1;
    }

    // 1,7,4
    // 6, -3
    // 1 +
    public static int wiggleRec(int[] nums, int i, int difference) {
        if(i == nums.length-2) {
            int diff = nums[nums.length-1] - nums[nums.length-2];
            if((diff < 0 &&  difference >= 0) || (diff >= 0 &&  difference < 0))
                return -1;
            else
                return -2;
        }
        int next = wiggleRec(nums, i+1, nums[i+1]-nums[i]);
        int nextSkip = wiggleRec(nums, i+1, nums[i+1]);
        return Math.max(1 + next == -1 ? 2 : 0, nextSkip == -1 ? 2 : 0);
    }

    public static void main(String[] args) {
        System.out.println(wiggleMaxLength(new int[]{1,7,4,9}));
    }
}
