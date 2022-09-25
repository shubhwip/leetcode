package medium.arraystrings;

import java.util.HashMap;
import java.util.Map;

public class SumOfEvenNumberAfterQueries {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        return sumEvenAfterQueriesMostOptimized(nums, queries);
    }

    // Copied
    // Most Optimized because of lower number of comparisons
    public int[] sumEvenAfterQueriesMostOptimized(int[] nums, int[][] queries) {
        int n = queries.length;
        int sum = 0;
        int[] answer = new int[n];

        for (int value : nums) {
            if ((value & 1) == 0)
                sum += value;
        }

        for (int i = 0; i < n; i++) {
            int val = queries[i][0];
            int index = queries[i][1];

            if ((nums[index] & 1) == 0)
                sum -= nums[index];

            nums[index] += val;

            if ((nums[index] & 1) == 0)
                sum += nums[index];

            answer[i] = sum;
        }

        return answer;
    }

    // Accepted in one go
    // But Slower ~37 ms
    /**
     Runtime: 46 ms, faster than 5.47% of Java online submissions for Sum of       Even Numbers After Queries.
     Memory Usage: 72 MB, less than 51.45% of Java online submissions for Sum of Even Numbers After Queries.
     **/
    public int[] sumEvenAfterQueriesNaive(int[] nums, int[][] queries) {

        // Length of nums array
        int len = nums.length;

        // Output result
        int[] sumResult = new int[len];

        // map of even numbers with position as key and value as number
        Map<Integer, Integer> map = new HashMap<>();

        // Precalculating evenSum O(n)
        int evenSum = 0;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] % 2 == 0) {
                map.put(i, nums[i]);
                evenSum += nums[i];
            }
        }

        // Executing Queries
        for(int j=0; j<queries.length; j++) {
            //System.out.println("Even Sum 1 " + evenSum);
            int val = queries[j][0];
            int index = queries[j][1];
            nums[index] = nums[index] + val;
            // After executing query even number
            if(nums[index] % 2 == 0) {
                // Even earlier as well
                if(map.containsKey(index)) {
                    evenSum -= map.get(index);
                }
                evenSum += nums[index];
                //System.out.println("Even Sum 2 " + evenSum);
                map.put(index, nums[index]);
            } else {
                // Now after query became odd
                // Earlier even
                // Update evenSum
                if(map.containsKey(index)) {
                    evenSum -= map.get(index);
                }
                //System.out.println("Even Sum 3 " + evenSum);
                // Remove keys
                map.remove(index);
            }

            sumResult[j] = evenSum;
            //System.out.println("Even Sum sumResult" + j + " " + sumResult[j]);
        }

        return sumResult;
    }

    // Better
    /**
     58 / 58 test cases passed.
     Status: Accepted
     Runtime: 14 ms
     Memory Usage: 72.7 MB
     */
    public int[] sumEvenAfterQueriesOptimized(int[] nums, int[][] queries) {

        // Length of nums array
        int len = nums.length;

        // Output result
        int[] sumResult = new int[len];

        // Precalculating evenSum O(n)
        int evenSum = 0;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] % 2 == 0) {
                evenSum += nums[i];
            }
        }

        // Executing Queries
        for(int j=0; j<queries.length; j++) {
            //System.out.println("Even Sum 1 " + evenSum);
            int val = queries[j][0];
            int index = queries[j][1];
            int temp = nums[index] + val;
            // After executing query even number
            if(temp % 2 == 0) {
                // Even earlier as well
                if(nums[index] % 2 == 0) {
                    evenSum -= nums[index];
                }
                evenSum += temp;
                //System.out.println("Even Sum 2 " + evenSum);
            } else {
                // Now after query became odd
                // Earlier even
                // Update evenSum
                if(nums[index] % 2 == 0) {
                    evenSum -= nums[index];
                }
                //System.out.println("Even Sum 3 " + evenSum);
                // Remove keys
                //map.remove(index);
            }
            nums[index] = temp;
            sumResult[j] = evenSum;
            //System.out.println("Even Sum sumResult" + j + " " + sumResult[j]);
        }

        return sumResult;
    }

}
