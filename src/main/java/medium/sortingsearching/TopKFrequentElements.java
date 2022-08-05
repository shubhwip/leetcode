package medium.sortingsearching;

import java.util.*;
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
    public int[] topKFrequentNaive(int[] nums, int k) {
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

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){ map.put(num, map.getOrDefault(num, 0) + 1); }

        Queue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        Queue<String> heap1 = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1) != map.get(o2))
                    return map.get(o2)-map.get(o1);
                else
                    return o1.compareTo(o2);
            }
        });

        for(int key : map.keySet()){ heap.add(key); }

        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++){
            ans.add(heap.poll());
        }

        return ans.stream().mapToInt(a->a).toArray();
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{17,17,17,18,18,19}, 2));
    }
}
