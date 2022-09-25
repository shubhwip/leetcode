package medium.treegraphs;

import java.util.*;

//https://leetcode.com/problems/binary-trees-with-factors
public class BinaryTreeWithFactors {

    // Fastest1
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, mod = 1_000_000_007;
        long[] dp = new long[n];
        long res = 0;
        Map<Integer, Integer> idxs = new HashMap();
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j]*arr[j] > arr[i]) break;
                if (arr[i] % arr[j] == 0 && idxs.containsKey(arr[i]/arr[j])) {
                    if (arr[j]*arr[j] == arr[i]) {
                        dp[i] += dp[j]*dp[j];
                        dp[i] %= mod;
                    }
                    else {
                        dp[i] += 2*dp[j]*dp[idxs.get(arr[i]/arr[j])];
                        dp[i] %= mod;
                    }
                }
            }
            idxs.put(arr[i], i);
            res += dp[i];
            res %= mod;
        }
        return (int) res;
    }

    // Fastest2
    public int numFactoredBinaryTrees3(int[] A) {
        long res = 0L, mod = (long)1e9 + 7;
        Arrays.sort(A);
        HashMap<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            dp.put(A[i], 1L);
            for (int j = 0; j < i; ++j)
                if (A[i] % A[j] == 0)
                    dp.put(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.getOrDefault(A[i] / A[j], 0L)) % mod);
            res = (res + dp.get(A[i])) % mod;
        }
        return (int) res;
    }

    // Fastest 3
    public int numFactoredBinaryTrees2(int[] A) {
        Arrays.sort(A);
        Map<Integer, Long> map = new HashMap();
        long count = 1;
        map.put(A[0], count);
        for (int i = 1; i < A.length; i++) {
            count = 1;
            for (Integer n : map.keySet()) {
                if (A[i] % n == 0 && map.containsKey(A[i] / n)) {
                    count += map.get(n) * map.get(A[i] / n);
                }
            }
            map.put(A[i], count);
        }
        long sum = 0;
        for (Integer n : map.keySet()) {
            sum = (sum + map.get(n)) % ((int) Math.pow(10, 9) + 7) ;
        }
        return (int) sum;
    }


    int mod = 1000000000 + 7;
    Map<Integer, Integer> map;
    public int numFactoredBinaryTrees1(int[] arr) {
        map = new HashMap<>();
        Set<Integer> s = new HashSet<>();
        for(int a : arr) {
            s.add(a);
        }
        int count = 0;
        for(int i=0;i<arr.length;i++) {
            count += 1;
            count += numFactoredBinaryTreesRecursion(arr, s, arr[i], -1);
            count %= mod;
        }
        return count;
    }

    public int numFactoredBinaryTreesRecursion(int[] arr, Set<Integer> s, int root, int picked) {
        if(picked > root)
            return 0;
        int count = 0;
        if(picked != -1 && picked < root) {
            if(root % picked == 0) {
                int left = picked;
                int right = root/picked;
                if(s.contains(right)) {
                    count += 1 + map.get(left) + map.get(right);
                    count %= mod;
                }
            }
        }
        for(int i=0;i<arr.length && arr[i] != root;i++) {
            count += numFactoredBinaryTreesRecursion(arr, s, root, arr[i]);
            count %= mod;
        }
        count %= mod;
        map.put(root, count);
        return count;
    }
}
