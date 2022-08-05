package medium.sortingsearching;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentStrings {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            map.merge(word, 1, Integer::sum);
        }
        // Writing Correct Comparator for Sorting was the key here
        // Otherwise completely similar to Top K Frequent Elements
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1) != map.get(o2))
                    return map.get(o2)-map.get(o1);
                else
                    return o1.compareTo(o2);
            }
        });
        for(String s : map.keySet()) {
            queue.add(s);
        }
        List<String> l = new ArrayList<>();
        for(int i=0;i<k;i++) {
            l.add(queue.poll());
        }
        return l;
    }
}
