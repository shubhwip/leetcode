package hard.divideconquer;

// Mistakes I did
// 1. I chose pivot as 1 when I could've chosesn easily 0
// 2. Once placing pivot as 1 in correct position, I assumed rest of array sorted which led to some test cases failures
// Problem : https://leetcode.com/problems/first-missing-positive/
// Explanation : https://leetcode.com/problems/first-missing-positive/discuss/319270/Explanation-of-crucial-observation-needed-to-deduce-algorithm
public class FindSmallestPositiveInteger {
    public static int partition(int[] nums, int left, int right, int pivotPos) {
        int j = left-1;
        for(int i=left;i<=right;i++) {
            if(nums[i] > 0) {
                j++;
                swap(nums, i, j);
            }
        }
        //swap(nums, j+1, right);
        return j;
    }

    public static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    // 112 / 173 test cases passed.
    public static int firstMissingPositive(int[] nums) {
        int minimum = Integer.MAX_VALUE;
        int minimumPos = -1;
        int numsLen = nums.length;
        // Minimum Pos and Num Selection can be avoided
        for(int i = 0;i < numsLen; i++) {
            if(nums[i] > 0 && minimum > nums[i]) {
                minimum = nums[i];
                minimumPos = i;
            }
        }
        if(minimumPos == -1) {
            return 1;
        }
        else if(minimum > 1) {
            return 1;
        }
        else {
            int pivotPos = partition(nums, 0, numsLen-1, minimumPos)+1;
            // Here I assumed numbers are sorted
            // Bad Assumption
            for(int i=0;i<pivotPos;i++) {
                int k = Math.abs(nums[i]);
                if(k<=pivotPos)
                    nums[k-1]=(nums[k-1]<0)?nums[k-1]:-nums[k-1];
            }
            for(int i=0;i<pivotPos;i++) {
                if(nums[i] > 0)
                    return i+1;
            }
            return pivotPos+1;
        }
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1,2,6,3,5,4}));
        System.out.println(firstMissingPositive(new int[]{0,1,2}));
    }
}
