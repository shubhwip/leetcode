package easy.others;

public class MissingNumber {
    // Multiple Approaches
    // https://www.geeksforgeeks.org/find-the-missing-number/
    // Approach 1 Sum
    public int missingNumber(int[] nums) {
        int s = nums.length;
        int sum = (s * (s + 1)) / 2;
        for (int i = 0; i < nums.length; i++) {
            sum = sum - nums[i];
        }
        return sum;
    }

    // Approach 2 Modified Sum for Integer Overflow

    // XOR Approach

    // Boolean Array Approach


}
