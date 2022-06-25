package easy.array;

public class BuySellStock2 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        //7,1,5,3,6,4
        //1,2,3,4,5
        //7,1,5,4,20,4
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
