package easy;

import java.util.Arrays;

public class SqaureSortedArray {

    public static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        int min = Integer.MAX_VALUE;
        int pos = 0;
        int squares[] = new int[nums.length];
        int p = 0;
        for (p = 0; p < nums.length; p++) {
            int a = nums[p] < 0 ? nums[p]*(-1) : nums[p];
            if (a < min) {
                min = a;
                pos = p;
            }
        }
        squares[0] = nums[pos] * nums[pos];
        int k = 1;
        int i = pos - 1;
        int j = pos + 1;
        for (i = pos - 1, j = pos + 1; i >= 0 && j < nums.length; ) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a < b) {
                squares[k] = a;
                k++;
                i--;
            } else {
                squares[k] = b;
                k++;
                j++;
            }
        }
        while (i >= 0) {
            squares[k] = nums[i] * nums[i];
            k++;
            i--;
        }
        while (j < nums.length) {
            squares[k] = nums[j] * nums[j];
            k++;
            j++;
        }
        return squares;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{-4, -1, 0, 3, 10};
        int[] nums = new int[]{-5, -3, -2, -1};
        //int[] nums = new int[]{0, 2};
        Arrays.stream(Arrays.asList(sortedSquares(nums)).stream().toArray()).map(a -> a.toString()).forEach(System.out::println);
    }
}
