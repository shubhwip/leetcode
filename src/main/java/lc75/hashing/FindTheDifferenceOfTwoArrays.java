package lc75.hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindTheDifferenceOfTwoArrays {

    // First Pass : Slow 11 ms Beats 53.38% of users with Java
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> s1 = new HashSet<>();
        for(int n : nums1) {
            s1.add(n);
        }
        for(int n : nums2) {
            s1.remove(n);
        }
        result.add(s1.stream().collect(Collectors.toList()));
        s1.clear();
        for(int n : nums2) {
            s1.add(n);
        }
        for(int n : nums1) {
            s1.remove(n);
        }
        result.add(s1.stream().collect(Collectors.toList()));
        return result;
    }
}
