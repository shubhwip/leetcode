package easycard.math;

public class CountPrimes {

    public static int countPrimes(int n) {
        boolean A[] = new boolean[n + 1];
        for (int i = 2; i < n; i++) {
            A[i] = true;
        }
        int p = 0;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (A[i]) {
                for (int j = i * i; j < n; j = (i * i + p * i)) {
                    A[j] = false;
                    p++;
                }
            }
        }
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (A[i])
                cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }
}
