package lc75.hashing;

import java.util.*;

public class UniqueNumberOfOccurences {

    // 2ms 1st Pass Accepted
    public boolean uniqueOccurrences1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int n : arr) {
            map.merge(n, 1, Integer::sum);
        }
        for(int n : map.values() ) {
            if(set.contains(n)) {
                return false;
            }
            set.add(n);
        }
        return true;
    }

    public boolean uniqueOccurrences2(int[] arr) {
        int[] counter = new int[2002];
        for(int n : arr) {
            if(n < 0) {
                counter[n+1001]++;
            }
            counter[n]++;
        }
        Arrays.sort(counter);
        for(int i=1; i<counter.length; i++) {
            if(counter[i] == counter[i-1])
                return false;
        }
        return true;
    }

    // Most Optimised
    public boolean uniqueOccurrences(int[] arr) {
        int[] num = new int[2001];
        boolean[] check = new boolean[1001];
        for(int i : arr) num[1000 + i]++;
        for(int i : arr){
            int count = num[1000 + i];
            num[1000 + i] = 0;
            if(count > 0){
                if(check[count]) return false;
                else check[count] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
