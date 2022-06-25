package medium.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    static List<List<Integer>> l = new ArrayList<>();

    // Slowest Solution
    // 10 / 10 test cases passed.
    // Status: Accepted
    // Runtime: 2 ms
    // Memory Usage: 40.3 MB
    // You are here!
    //Your memory usage beats 12.03 % of java submissions.
    public static List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0)
            return l;
        List<Integer> p = new ArrayList<>();
        backtrack(nums, p, 0, 0);
        return l;
    }

    private static void backtrack(int[] nums, List<Integer> p, int count, int x) {
        if (count == Math.pow(2, nums.length))
            return;
        else if (p.size() > nums.length)
            return;
        else if (p.size() <= nums.length) {
            l.add(p);
        }
        for (int i = x; i < nums.length; i++) {
            List<Integer> a = new ArrayList<>(p);
            a.add(nums[i]);
            backtrack(nums, a, count + 1, i + 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2}));
    }
}
