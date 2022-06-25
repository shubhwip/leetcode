package medium.sortingsearching;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TopKFrequentElements {

    // Very Slow Solution
    // 21 / 21 test cases passed.
    //Status: Accepted
    //Runtime: 39 ms
    //Memory Usage: 46.6 MB
    // You are here!
    //Your runtime beats 5.02 % of java submissions.
    // You are here!
    //Your memory usage beats 34.91 % of java submissions.
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        Map<Integer, Integer> sortMap =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int[] a = new int[k];
        int j = 0;
        for (Integer p : sortMap.keySet()) {
            if (j == k)
                break;
            a[j] = p;
            j++;
        }
        return a;
    }
}
