package easy.sortingsearching;

public class SortArrayByParity {
    // WOW
    // In place Worst case O(n)
    // Time Complexity O(1)
    // 1ms
    public static void partition(int[] nums, int left, int right) {
        int i = left - 1;
        for(int j=left;j<right;j++) {
            if(nums[j] % 2 == 0) {
                i++;
                swap(nums, i, j);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] sortArrayByParity(int[] nums) {
        partition(nums, 0 ,nums.length);
        return nums;
    }

    // Time Complexity : O(2n)
    // Space Complexity : O(n)
    // 2ms
    public int[] sortArrayByParityNaive(int[] nums) {
        int numsLen = nums.length;
        int[] res = new int[numsLen];
        int k=0;
        for(int i=0;i<numsLen;i++) {
            if(nums[i] % 2 == 0 && k<numsLen) {
                res[k] = nums[i];
                k++;
            }
        }
        for(int i=0;i<numsLen;i++) {
            if(nums[i] % 2 != 0 && k<numsLen) {
                res[k] = nums[i];
                k++;
            }
        }
        return res;
    }
}
