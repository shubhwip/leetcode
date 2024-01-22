package lc75.slidingwindow;

//https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
public class LongestSubArrayAfterDeleting1OneTime {

    // Whoa This was wow
    // Solved in one go
    // 1
    //ms
    //Beats
    //100.00%
    //of users with Java
    public static int longestSubarray(int[] nums) {
        int right = 0;
        int len = nums.length;
        int ones = 0;
        int max = Integer.MIN_VALUE;
        boolean zeroSeen = false;
        while(right < len) {
            if(nums[right] != 1) {
                zeroSeen = true;
                int zeroes = 0;
                int newCnt = 0;
                while(right < len && nums[right] != 1) {
                    right++;
                    zeroes++;
                }
                if(zeroes == 1) {
                    while (right < len && nums[right] == 1) {
                        right++;
                        newCnt++;
                    }
                } else {
                    ones = 0;
                }
                max = Math.max(max, newCnt + ones);
                ones = newCnt;
            }
            while(right < len && nums[right] == 1) {
                right++;
                ones++;
            }
            max = Math.max(max, ones);
        }
        return zeroSeen ?  max : ones - 1;
    }

    public static int longestSubarray1(int[] nums) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        int leftCnt = 0;
        int rightCnt = 0;
        int max = Integer.MIN_VALUE;
        int zeroes = 0;
        while(right < len) {
            if(nums[right] == 0) {
                while(right < len && nums[right] == 0) {
                    right++;
                    zeroes++;
                }
                if(zeroes > 1) {
                    leftCnt = 0;
                }
                while(right < len && nums[right] == 1) {
                    right++;
                    rightCnt++;
                }
                max = Math.max(max, leftCnt+rightCnt);
                leftCnt = rightCnt;
                rightCnt = 0;
            }
            while(right < len && nums[right] == 1) {
                right++;
                leftCnt++;
            }
            max = Math.max(max, leftCnt+rightCnt-1);
            right++;
        }
        return Math.max(max, leftCnt+rightCnt);
    }

    public static void main(String[] args) {
        //System.out.println(longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
        //System.out.println(longestSubarray(new int[]{1,1,0,1}));
        System.out.println(longestSubarray(new int[]{1,1,1}));

    }

}
