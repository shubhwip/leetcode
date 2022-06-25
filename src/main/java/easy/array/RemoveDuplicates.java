package easy.array;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                count++;
                nums[count - 1] = nums[i];
            }
        }
        return count;
    }
}
