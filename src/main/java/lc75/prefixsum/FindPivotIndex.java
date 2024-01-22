package lc75.prefixsum;

public class FindPivotIndex {
    // 1st Pass : 549 / 746 testcases passed
    // 2nd Pass : 651 / 746 testcases passed
    // 3rd Pass : Passed
    public static int pivotIndex(int[] nums) {
        int len = nums.length;
        if(len == 1) {
            return 0;
        }
        int[] temp = new int[nums.length];
        temp[0] = 0;
        for(int i=1; i<nums.length; i++) {
            // IDE help from nums[i] to nums[i-1];
            temp[i] = temp[i-1] + nums[i-1];
        }
        int prefixSum = 0;
        int indexFound = -1;
        if(prefixSum == temp[nums.length-1]) {
            indexFound = nums.length - 1;
        }
        for(int i=nums.length-2; i>=0; i--) {
            // IDE help from nums[i] to nums[i+1];
            prefixSum += nums[i+1];
            if(prefixSum == temp[i]) {
                // After 1st Pass.
                indexFound = i;
            }
        }
        return indexFound;
    }

    public static void main(String[] args) {
       // System.out.println(pivotIndex(new int[]{-1,-1,0,0,-1,-1}));
        System.out.println(pivotIndex(new int[]{-1,-1,0,1,1,-1}));

    }
}
