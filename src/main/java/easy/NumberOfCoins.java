package easy;

public class NumberOfCoins {

    private static int getNumberOfCoins(int v, int m, int[] coins) {
        if (v < 0)
            return 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] == v) {
                return 1;
            }
        }
        for (int i = 0; i < coins.length - 1; i++)
            return 1 + Integer.min(getNumberOfCoins(v - coins[i], m, coins), getNumberOfCoins(v - coins[i + 1], m, coins));

        return -1;
    }

    public static void main(String[] args) {

//        int v = 30;
//        int m = 3;
//        int[] coins = new int[]{25, 10, 5};
        int v = 11;
        int m = 4;
        int[] coins = new int[]{9, 6, 5, 1};
        System.out.println(getNumberOfCoins(v, m, coins));

    }
}
