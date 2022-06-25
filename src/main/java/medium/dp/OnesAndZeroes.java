package medium.dp;

import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/ones-and-zeroes/
// Requires Revision
// Nice Explanation : https://leetcode.com/problems/ones-and-zeroes/discuss/814077/Dedicated-to-Beginners
public class OnesAndZeroes {

    // Naive way
    // Loop through all the strings and calculate count of 0's and 1's and then decide longest
    // O(str.lengthXstrs.length)
     static List<Pair<Integer, Integer>> zeroOnes;
     static int[][][] dp;
    public int findMaxForm(String[] strs, int m, int n) {
         dp = new int[601][101][101];
         for(int[][] row: dp) {
             for(int[] r: row) {
                 Arrays.fill(r, -1);
             }
         }
         zeroOnes = new ArrayList<>();
         for(String str: strs) {
             int zeroes = calculate(str, '0');
             int ones = str.length()-zeroes;
             zeroOnes.add(new Pair(zeroes, ones));
         }
        return findMaxFormTab(strs, m, n, 0);
    }

    public static int findMaxFormTab(String[] strs, int m, int n, int index) {
        int[][] tab = new int[m+1][n+1];
        for(String str: strs) {
            int x = calculate(str, '1');
            int y = str.length()-x;
            for(int i=m;i>=y;i--)
            {
                for(int j=n;j>=x;j--)
                {
                    tab[i][j] = Math.max(tab[i][j],1+tab[i-y][j-x]);
                }
            }
        }
        return tab[m][n];
    }


    // Memoization
    // Memory Limit Exceeded
    // Passed Once as well
     public static int findMaxFormMemo(String[] strs, int m, int n, int index) {
         if(index == strs.length || (m == 0 && n == 0))
             return 0;
         if(dp[index][m][n] != -1)
             return dp[index][m][n];
         if(m < zeroOnes.get(index).getKey() || n < zeroOnes.get(index).getValue())
             return dp[index][m][n] = findMaxFormMemo(strs, m , n, index+1);
         int include = 1 + findMaxFormMemo(strs, m-zeroOnes.get(index).getKey() , n-zeroOnes.get(index).getValue(), index+1);
         int exclude = findMaxFormMemo(strs, m, n, index+1);
         return dp[index][m][n] = Math.max(include, exclude);
     }

    // Recursive TLE
    // Passed only 22/72 Test Cases.
     public static int findMaxFormRecursive(String[] strs, int m, int n, int index) {
         if(index == strs.length || (m == 0 && n == 0))
             return 0;
         if(m < zeroOnes.get(index).getKey() || n < zeroOnes.get(index).getValue())
             return findMaxFormRecursive(strs, m , n, index+1);
         int include = 1 + findMaxFormRecursive(strs, m-zeroOnes.get(index).getKey() , n-zeroOnes.get(index).getValue(), index+1);
         int exclude = findMaxFormRecursive(strs, m, n, index+1);
         return Math.max(include, exclude);
     }

    public static int calculate(String str, char c) {
        int count = 0;
        for(char ch : str.toCharArray()) {
            if(ch == c)
                count++;
        }
        return count;
    }


}
