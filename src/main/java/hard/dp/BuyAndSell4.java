package hard.dp;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/2480299/Python-Striver-Solution-O(N*K)-Easy-Solution
public class BuyAndSell4 {
    public static int maxProfit(int k, int[] prices) {
        return maxProfitRecursion(k, prices, 'b', 0, 0, 0);
    }

    public static int maxProfitRecursion(int k, int[] prices, char activity, int index, int buyValue, int currentProfit) {
        if(index >= prices.length || k <= 0)
            return 0;
        int maxProfit = 0;
        if(activity == 'b') {
            for(int i=index; i<prices.length; i++) {
                maxProfitRecursion(k, prices, 's', i+1, prices[i], currentProfit);
            }
        } else {
            for(int i=index;i<prices.length;i++) {
                if(buyValue > prices[i])
                    continue;
                int prof = currentProfit + prices[i] - buyValue;
                int res = prof + maxProfitRecursion(k--, prices, 'b', i+1, 0, prof);
                maxProfit = Math.max(maxProfit, res);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3}));
    }
}
