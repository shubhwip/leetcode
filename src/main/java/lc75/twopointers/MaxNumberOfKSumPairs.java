package lc75.twopointers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxNumberOfKSumPairs {
    public static int maxOperations1(int[] nums, int k) {
        Set<Integer> s = new HashSet<>();
        int cnt = 0;
        for(int n : nums) {
            if(s.contains(n) && (n+n) == k) {
                s.remove(n);
                cnt++;
                continue;
            } else {
                s.add(n);
            }
            if(s.contains(n) && (k-n) != n && s.contains(k-n)) {
                s.remove(n);
                s.remove(k-n);
                cnt++;
            }
        }
        return cnt;
    }

    public static int maxOperations(int[] nums, int k) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) {
            map.merge(n, 1, Integer::sum);
        }
        Set<Integer> ignored = new HashSet<>();
        for(int key : map.keySet()) {
            if(ignored.contains(key) && ignored.contains(k-key))
                continue;
            if(map.containsKey(k-key)) {
                cnt += Math.min(map.get(key), map.get(k-key));
            }
            ignored.add(key);
            ignored.add(k-key);
        }
        return cnt;
    }


    public static void main(String[] args) {
        //System.out.println(maxOperations(new int[]{2,2,2,3,1,1,4,1}, 4));
        System.out.println(maxOperations(new int[]{2,5,4,4,1,3,4,4,1,4,4,1,2,1,2,2,3,2,4,2}, 3));
    }
}
