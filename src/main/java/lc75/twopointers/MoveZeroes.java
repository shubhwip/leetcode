package lc75.twopointers;

public class MoveZeroes {
    public static void moveZeroes1(int[] nums) {
        int k=0;
        for(int i=0; i<nums.length;i++) {
            if(nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for(int i=k;i<nums.length;i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroes(int[] nums) {
        int k = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] != 0) {
                swap(nums, i, k);
                k++;
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        moveZeroes(new int[]{0,1,0,3,12});
    }
}
