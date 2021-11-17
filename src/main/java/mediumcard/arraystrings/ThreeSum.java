package mediumcard.arraystrings;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {


    // Another Approach O(n*n)
    // Pick one element and Then solve two sum problem
    // Solved with 24ms now
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> nes = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) //Amazing Improvement Since array is sorted once number is positive then zerosum not possible
                break;
            if (i == 0 || nums[i] != nums[i - 1]) {
                int targetSum = 0 - nums[i];
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[j] + nums[k];
                    if (sum == targetSum) {
                        List<Integer> l = new ArrayList<>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        nes.add(l);
                        while (j < k && nums[j] == nums[j + 1])
                            j++;
                        while (j < k && nums[k] == nums[k - 1])
                            k--;
                        k--;
                        j++;
                    } else if (sum > targetSum) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }
        // Answers can be wrong here in case when elements are not positioned the way we think
        return nes;
    }

    // Another Approach O(n*n)
    // Pick one element and Then solve two sum problem
    // 341 ms taken - too slow
    public static List<List<Integer>> threeSumAcceptedAnswer(int[] nums) {
        Set<List<Integer>> nes = new HashSet<>();
        nums = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < nums.length - 1; i++) {
            int targetSum = (-1) * nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j > i && j < k) {
                int sum = nums[j] + nums[k];
                if (sum == targetSum) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    nes.add(l);
                    k--;
                    j++;
                } else if (sum > targetSum) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        // Answers can be wrong here in case when elements are not positioned the way we think
        return nes.stream().collect(Collectors.toList());
    }

    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        // Brute Force // O(n*n*n*)*O(n*n*n)
        // Finding List Duplicacy Making it Medium Level
        // Also Reducing Complexity of the solution
        List<List<Integer>> nes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> l = new ArrayList<>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        if (!duplicate(nes, l)) {
                            nes.add(l);
                        }
                    }
                }
            }
        }
        return nes;
    }

    private boolean duplicate(List<List<Integer>> nes, List<Integer> l) {

        for (int i = 0; i < nes.size(); i++) {
            int a = 0;
            for (int j = 0; j < nes.get(i).size(); j++) {
                for (int k = 0; k < l.size(); k++) {
                    if (l.get(k) == nes.get(i).get(j))
                        a++;
                }
            }
            if (a == 3)
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{-1, 5, 0, 1, 5, -1, -4}));
        //7/318 passed
        System.out.println(threeSum(new int[]{0, 0, 0})); // failed
        // 152/318 passed
//        Input:
//[-2,0,1,1,2]
//        Output:
//[[-2,0,2]]
//        Expected:
//[[-2,0,2],[-2,1,1]]
        // Silly Mistake
        System.out.println(threeSum(new int[]{-2, 0, 1, 1, 2})); // failed
    }
}
