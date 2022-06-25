package medium.dp;

import java.util.Arrays;

public class CoinChange {

    public static int[][] dp = new int[10000 + 1][12 + 1]; // create empty DP table

    public static int coinChange(int[] coins, int amount) {
        //int minCoins = coinChangeRecursiveShubhamImplementation(coins, amount, 0 ,0,0);
        //int minCoins = coinChangeRecursive2(coins, amount, coins.length);
        for (int[] row: dp)
            Arrays.fill(row, -1);
        int minCoins = coinChangeMemoization(coins, amount, coins.length);
        return minCoins == Integer.MAX_VALUE - 1 ? -1 : minCoins;
    }

    // TLE
    // 39 / 189 test cases passed.
    // Last executed input:
    // [411,412,413,414,415,416,417,418,419,420,421,422]
    // 9864
    public static int coinChangeRecursiveShubhamImplementation(int[] coins, int amount, int j, int temp, int count) {
        if(temp == amount) {
            return count;
        } if(temp > amount) {
            return Integer.MAX_VALUE;
        }
        int minCoinChange = Integer.MAX_VALUE;
        for(int i=j;i<coins.length;i++) {
            int cnt = count + 1;
            minCoinChange = Math.min(minCoinChange, coinChangeRecursiveShubhamImplementation(coins, amount, i, temp + coins[i], cnt));
        }
        return minCoinChange;
    }

    public static int coinChangeRecursive2(int[] coins, int amount, int arraySize) {
        if (arraySize == 0 || amount == 0)
            return (amount == 0) ? 0 : Integer.MAX_VALUE - 1;

        if (coins[arraySize - 1] > amount)
            return 0 + coinChangeRecursive2(coins, amount - 0, arraySize - 1);
        else
            return Math.min(0 + coinChangeRecursive2(coins, amount - 0, arraySize - 1),
                    1 + coinChangeRecursive2(coins, amount - coins[arraySize - 1], arraySize));
    }

    public static int coinChangeMemoization(int[] coins, int amount, int arraySize) {
        if (arraySize == 0 || amount == 0)
            return (amount == 0) ? 0 : Integer.MAX_VALUE - 1;
        if(dp[amount][arraySize] != -1)
            return dp[amount][arraySize];
        if (coins[arraySize - 1] > amount)
            return dp[amount][arraySize] = 0 + coinChangeMemoization(coins, amount - 0, arraySize - 1);
        else
            return dp[amount][arraySize] = Math.min(0 + coinChangeMemoization(coins, amount - 0, arraySize - 1),
                    1 + coinChangeMemoization(coins, amount - coins[arraySize - 1], arraySize));
    }

    public static int coinChangeTabulation1(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int coin: coins) {
         for(int i = coin;i<amount;i++) {
             dp[i] = Math.min(dp[i], dp[i-coin]+1);
         }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int coinChangeTabulation2(int[] coins, int amount) {
//        int Max = amount + 1;
//        vector<int> dp(amount + 1, Max);
//        dp[0] = 0;
//        for (int i = 1; i <= amount; i++) {
//            for (int j = 0; j < coins.size(); j++) {
//                if (coins[j] <= i) {
//                    dp[i] = min(dp[i], dp[i - coins[j]] + 1);
//                }
//            }
//        }
//        return dp[amount] > amount ? -1 : dp[amount];
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2}, 5));
        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChange(new int[]{1,3,5,7}, 8));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1}, 0));
        System.out.println(coinChange(new int[]{411,412,413,414,415,416,417,418,419,420,421,422}, 9864));
    }
}
