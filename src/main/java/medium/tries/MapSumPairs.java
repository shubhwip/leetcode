package medium.tries;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/map-sum-pairs/
//Naive Implementation using HashMap directly
public class MapSumPairs {
    Map<String, Integer> map;

    public MapSumPairs() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        int sum = 0;
        for(String key : map.keySet()) {
            if(key.startsWith(prefix)) {
                sum += map.get(key);
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
