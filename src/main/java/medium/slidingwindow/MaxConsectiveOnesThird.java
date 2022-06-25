package medium.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/max-consecutive-ones-iii/
public class MaxConsectiveOnesThird {

    //COPIED
    //https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/javacpython-sliding-window/379427?page=3
    public static int longestOnes(int[] nums, int k) {
        int l=0,r=0;
        while(r<nums.length) {
            if(nums[r] == 0)
                k--;
            if(k<0 && nums[l++] == 0 )
                k++;
            r++;
        }
        return r-l;
    }

    // Some cases passing only
    // Bad attempt https://leetcode.com/submissions/detail/723586388/
    // Wrote unnecessarily complicated solution
    public static int longestOnesBadAttempt(int[] nums, int k) {
        Queue<Integer> q = new LinkedList<>();
        int l=0,r=0;
        int replacedOnesLeft = k;
        int maxConsecutivesOnes = 0;
        int max = Integer.MIN_VALUE;
        while(r<nums.length) {
            if(nums[r] == 0) {
                if(replacedOnesLeft==0) {
                    max = Math.max(maxConsecutivesOnes, max);
                    int pos = q.remove();
                    if(pos > l+1) {
                        maxConsecutivesOnes -= (pos-l);
                    }
                    l = pos + 1;
                } else {
                    maxConsecutivesOnes += 1;
                    replacedOnesLeft--;
                }
                q.add(r);
            } else {
                maxConsecutivesOnes += 1;
            }
            r++;
        }
        return Math.max(maxConsecutivesOnes, max);
    }

    public static void main(String[] args) {
        //System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,1,0,1,1,1,1}, 3));
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,1,0,1,1,1,1}, 3));
    }
}
