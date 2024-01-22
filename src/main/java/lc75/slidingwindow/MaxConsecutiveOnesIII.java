package lc75.slidingwindow;

public class MaxConsecutiveOnesIII {

    // 1st pass 40/55 Test cases passed
    // 2nd pass 54 / 55 testcases passed
    // 3rd pass passed but very slow
    // 784
    //ms
    //Beats
    //5.01%
    //of users with Java
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int used = k;
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        while(right < nums.length) {
            // corner case k!=0
            if(used == 0) {
                max = Math.max(cnt, max);
                used = k;
                if(nums[left] == 0) {
                    left++;
                } else {
                    while (left < nums.length &&  nums[left] == 1) {
                        left++;
                        cnt++;
                    }
                }
                max = Math.max(cnt, max);
                right = left;
                cnt = 0;
            }
            while(right < nums.length && nums[right] == 1) {
                right++;
                cnt++;
            }
            while(right < nums.length && nums[right] == 0 && used != 0) {
                used--;
                right++;
                cnt++;
            }
            while(right < nums.length && nums[right] == 1) {
                right++;
                cnt++;
            }
            // fix for 1st pass corner case : when you can use atmost k's
            max = Math.max(cnt, max);
        }
        return max;
    }

    public static void main(String[] args) {
       // System.out.println(longestOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));
       // System.out.println(longestOnes(new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
        System.out.println(longestOnes(new int[]{0,0,1,1,1,0,0}, 0));
       // System.out.println(longestOnes(new int[]{1,1,1,1,1}, 0));
    }
}
