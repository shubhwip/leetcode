package mediumcard.arraystrings;

import java.util.ArrayList;
import java.util.List;

public class IncreasingTripletsSequence {
    // Amazing Solution
    // But I checked on discuss section
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3)
            return false;
        int n1 = Integer.MIN_VALUE;
        int n2 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= n1) n1 = n;
            else if (n <= n2) n2 = n;
            else
                return true;
        }
        return false;
    }

    // TLE
    public static boolean increasingTriplet4(int[] nums) {
        if (nums.length < 3)
            return false;
        List<Integer> l = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            boolean found = false;
            if (l1.contains(nums[i])) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    l.add(j);
                    found = true;
                }
            }
            if (!found)
                l1.add(nums[i]);
        }
        for (Integer p : l) {
            for (int i = p + 1; i < nums.length; i++) {
                if (nums[i] > nums[p])
                    return true;
            }
        }
        return false;
    }


    // 70/76, Optimization brought incorectness
    public static boolean increasingTriplet3(int[] nums) {
        if (nums.length < 3)
            return false;
        List<Integer> l = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (l1.contains(nums[i])) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (l1.contains(nums[j])) continue;
                if (nums[j] > nums[i]) {
                    l.add(j);
                    l1.add(nums[i]);
                    l1.add(nums[j]);
                    break;
                }
            }
        }
        for (Integer p : l) {
            for (int i = p + 1; i < nums.length; i++) {
                if (nums[i] > nums[p])
                    return true;
            }
        }
        return false;
    }

    // 75/76
    // Encountered MLE for large inputs
    // Need to find out optimization either on space or memory
    public static boolean increasingTriplet2(int[] nums) {
        if (nums.length < 3)
            return false;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (l.contains(nums[j])) // Minor Optimization but still TLE
                    continue;
                if (nums[j] > nums[i]) {
                    l.add(j);
                    break; // Minor optimization but still TLE
                }
            }
        }
        for (Integer p : l) {
            for (int i = p + 1; i < nums.length; i++) {
                if (nums[i] > nums[p])
                    return true;
            }
        }
        return false;
    }

    // Passed 65/76 testcases
    public static boolean increasingTriplet1(int[] nums) {
        if (nums.length < 3)
            return false;
        int p = 0;
        boolean found = false;
        for (int i = 0; i < nums.length; i++) {
            found = false;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    p = j;
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
        for (int i = p + 1; i < nums.length; i++) {
            if (nums[i] > nums[p])
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(increasingTriplet(new int[]{20, 100, 10, 12, 5, 13}));
        //System.out.println(increasingTriplet(new int[]{1, 5, 0, 4, 1, 3}));
    }
}
