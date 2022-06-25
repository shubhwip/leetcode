package medium.slidingwindow;

//https://leetcode.com/problems/count-number-of-nice-subarrays/
public class CountNumberOfNiceSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int l=0, r=0;
        int niceArrays = 0;
        while(r<nums.length) {
            if(nums[r]%2 != 0)
                k--;
            if(k==0) {
                niceArrays++;
                while(l< nums.length && nums[l]%2==0) {
                    niceArrays++;
                    l++;
                }
                k++;
                r++;
                while(r<nums.length && nums[r]%2==0) {
                    niceArrays++;
                    r++;
                }
                continue;
            }
            r++;
        }
        return niceArrays;
    }
}
