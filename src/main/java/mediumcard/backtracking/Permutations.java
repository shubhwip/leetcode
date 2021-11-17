package mediumcard.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {


    // Entire Solution Very Slow
    // 26/26 test cases passed
    // Runtime 7ms
    // Your runtime beats 5.44 % of java submissions.
    // Your memory usage beats 38.90 % of java submissions.
    static List<List<Integer>> l = new ArrayList<>();

    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> p = new ArrayList<>();
        backtrack(p, nums, Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return l;
    }

    private static void backtrack(List<Integer> p, int[] nums, List<Integer> q) {
        if (p.size() > nums.length)
            return;
        else if (p.size() == nums.length) {
            l.add(p);
        }
        for (int i = 0; i < q.size(); i++) {
            List<Integer> abc = new ArrayList<>(p);
            abc.add(q.get(i));
            List<Integer> s = buildList(q, i);
            backtrack(abc, nums, s);
        }
    }

    private static List<Integer> buildList(List<Integer> nums, int i) {
        return nums.stream().filter(a -> a != nums.get(i)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }


}
