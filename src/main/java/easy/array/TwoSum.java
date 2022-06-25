package easy.array;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] twoSums = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    twoSums[0] = i;
                    twoSums[1] = j;
                    return twoSums;
                }
            }
        }
        return twoSums;
    }
}
