package medium.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueryKthSmallestTrimmedNumber {

    static String[][] cache;
    public static int trim(String[] nums, int smallestNumber, int trimSize) {
        int len = nums[0].length();
        // Trimming the strings
        for(int i=0;i<nums.length;i++) {
            if(trimSize >= len)
                break;
            if(cache[trimSize][i] != "-1")
                break;
            cache[trimSize][i] = nums[i].substring(len-trimSize, len);
        }

        // Creating Heap
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //long a1 = (Long.parseLong(o1) - Long.parseLong(o2));
                return Long.compare(Long.valueOf(o1), Long.valueOf(o2));
            }
        });
        if(trimSize < len ) {
            for (String n : cache[trimSize]) {
                queue.offer(n);
            }
        } else {
            for (String n : nums) {
                queue.offer(n);
            }
        }
        // Finding Smallest Number
        String min = "";
        int p = smallestNumber;
        while(p > 0) {
            min = queue.poll();
            p--;
        }

        // Finding Position of Smallest Number in cache Array
        int pos = -1;
        for(int i=0;i<nums.length;i++) {
            if(trimSize >= len && nums[i].equals(min) ) {
                pos = i;
                break;
            } else if(trimSize < len && cache[trimSize][i].equals(min)) {
                pos = i;
                break;
            }
        }
        return pos;

    }

    public static int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        cache = new String[nums[0].length()+1][nums.length];
        for(String[] c: cache) {
            Arrays.fill(c, "-1");
        }
        for(int i=0;i<queries.length;i++) {
            res[i] = trim(nums, queries[i][0], queries[i][1]);
        }
        for(int n : res) {
            System.out.println(n);
        }
        return res;
    }

    public static void main(String[] args) {
//        String[] nums = new String[]{"102","473","251","814"};
//        int[][] queries = new int[][]{{1,1},{2,3},{4,2},{1,2}};
        //String[] nums = new String[]{"24","37","96","04"};
        //int[][] queries = new int[][]{{2,1},{2,2}};
        String[] nums = new String[]{"64333639502","65953866768","17845691654","87148775908","58954177897","70439926174","48059986638","47548857440","18418180516","06364956881","01866627626","36824890579","14672385151","71207752868"};
        int[][] queries = new int[][]{{9,4},{6,1},{3,8},{12,9},{11,4},{4,9},{2,7},{10,3},{13,1},{13,1},{6,1},{5,10}};
        System.out.println(smallestTrimmedNumbers(nums, queries));
    }
}
