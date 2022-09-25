package medium.dp;

import java.util.*;

public class ReduceArraySizeToHalf {
        static Map<Integer, Integer> arrMap;
        static int[][] memo;
        public int minSetSize1(int[] arr) {
            /**
             Approach 1
             Initialize Map of arr element and its count and total length of array
             Go over the Map and try to pick all combinations one by one and see if we
             get the minimum one.
             */
            memo = new int[arr.length+1][arr.length+1];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }
            arrMap = new HashMap<>();
            for(int n : arr) {
                arrMap.merge(n, 1, Integer::sum);
            }
            int[] keyArr = new int[arrMap.keySet().size()];
            int i=0;
            for(int n : arrMap.keySet()) {
                keyArr[i] = n;
                //System.out.println(keyArr[i]);
                i++;
            }
            //System.out.println(arrMap);
            //return minSetSizeRecursion(arr, keyArr, arr.length, 0, 0);
            return minSetSizeMemo(arr, keyArr, arr.length, 0);
        }

        public int minSetSizeRecursion(int[] arr, int[] keyArr, int subsetSize, int resultSet, int index) {
            if(index > keyArr.length - 1 && subsetSize > arr.length/2)
                return Integer.MAX_VALUE;
            if(subsetSize <= arr.length/2) {
                return resultSet;
            }
            int pick = minSetSizeRecursion(arr, keyArr, subsetSize - arrMap.get(keyArr[index]), resultSet + 1, index + 1);
            int dontPick = minSetSizeRecursion(arr, keyArr, subsetSize, resultSet, index + 1);
            return Math.min(pick, dontPick);
        }

        public int minSetSizeMemo(int[] arr, int[] keyArr, int subsetSize, int index) {
            if(index > keyArr.length - 1 && subsetSize > arr.length/2)
                return Integer.MAX_VALUE - arr.length;
            if(subsetSize <= arr.length/2) {
                return 0;
            }
            if(memo[index][subsetSize] != -1)
                return memo[index][subsetSize];
            int pick = 1 + minSetSizeMemo(arr, keyArr, subsetSize - arrMap.get(keyArr[index]), index + 1);
            int dontPick = minSetSizeMemo(arr, keyArr, subsetSize, index + 1);
            return memo[index][subsetSize] = Math.min(pick, dontPick);
        }

        public static int minSetSize(int[] arr) {
            /**
             Approach 1
             Initialize Map of arr element and its count and total length of array
             Go over the Map and try to pick all combinations one by one and see if we
             get the minimum one.
             */
            memo = new int[arr.length+1][arr.length+1];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }
            Arrays.sort(arr);
            //System.out.println(arrMap);
            return minSetSizeR1(arr, arr.length, 0, 0);
            //return minSetSizeMemo(arr, keyArr, arr.length, 0);
        }

        public static int minSetSizeR1(int[] arr, int subsetSize, int resultSet, int index) {
            if(index > arr.length - 1 && subsetSize > arr.length/2)
                return Integer.MAX_VALUE;
            if(subsetSize <= arr.length/2) {
                return resultSet;
            }
            int count = 1;
            while((index+1) < arr.length && arr[index+1] == arr[index]) {
                index++;
                count++;
            }
            int pick = minSetSizeR1(arr, subsetSize - count, resultSet + 1, index + 1);
            int dontPick = minSetSizeR1(arr, subsetSize, resultSet, index + 1);
            return Math.min(pick, dontPick);
        }

    public static int minSetSizeM1(int[] arr, int subsetSize, int resultSet, int index) {
        if(index > arr.length - 1 && subsetSize > arr.length/2)
            return Integer.MAX_VALUE;
        if(subsetSize <= arr.length/2) {
            return resultSet;
        }
        if(memo[index][subsetSize] != -1)
            return memo[index][subsetSize];
        int count = 1;
        while((index+1) < arr.length && arr[index+1] == arr[index]) {
            index++;
            count++;
        }
        int pick = minSetSizeR1(arr, subsetSize - count, resultSet + 1, index + 1);
        int dontPick = minSetSizeR1(arr, subsetSize, resultSet, index + 1);
        return memo[index][subsetSize] = Math.min(pick, dontPick);
    }

    public static int minSetSize3(int[] arr) {
        Map<Integer, Integer> arrMap = new HashMap<>();
        for(int n : arr) {
            arrMap.merge(n, 1, Integer::sum);
        }
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> -arrMap.get(a)));
        for(int n : arrMap.keySet()) {
            pq.add(n);
        }
        int result = 0;
        int size = arr.length;
        while(!pq.isEmpty()) {
            if(size <= arr.length/2 ) {
                break;
            }
            else {
                size = size - arrMap.get(pq.poll());
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minSetSize3(new int[]{3,3,3,3,5,5,5,2,2,7}));
        System.out.println(minSetSize3(new int[]{7,7,7,7,7,7}));
    }
}
