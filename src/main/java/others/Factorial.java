package others;

import java.math.BigInteger;

// Hutch
public class Factorial {

    public static int facIter(int n) {
        int prod = 1;
        for (int i = 2; i <= n; i++) {
            prod *= i;
        }
        return prod;
    }

    public static BigInteger facIterBigInt(int n) {
        BigInteger prod = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            prod = prod.multiply(new BigInteger(String.valueOf(i)));
        }
        return prod;
    }

    // Tail Recursion Issue.
    public static int facRec(int n) {
        if (n == 1)
            return n;
        return n * facRec(n - 1);
    }

    // Tail Recursion Issue.
    public static int facRecTail(int n, int res) {
        if (n == 1)
            return res;
        return facRecTail(n - 1, n * res);
    }

    public static BigInteger facRecBigDecimal(BigInteger n, BigInteger res) {
        if (n.equals(1))
            return res;
        return facRecBigDecimal(n.subtract(new BigInteger("1")), n.multiply(res));
    }

    // Tail Recursion Issue.
    public static int facRec(int n, int res) {
        if (n == 1)
            return res;
        return facRec(n - 1, n * res);
    }


    public static void facLarge(int n) {
        int[] num = new int[200];
        int i = 2;
        num[0] = 1;
        int rem = 0;
        while (i <= n) {
            int carry = 0;
            for (int j = 0; j < num.length; j++) {
                int res = num[j] * i + carry;
                carry = res / 10;
                rem = res % 10;
                num[j] = rem;
            }
            i++;
        }
        i = num.length - 1;
        while (i > 0 && num[i] == 0)
            --i;
        while (i >= 0)
            System.out.print(num[i--]);
    }

    public static void main(String[] args) {
        System.out.println(facIter(10));
        System.out.println(facRec(10, 1));
        facLarge(100);
        System.out.println();
        System.out.println(facIterBigInt(100));
    }
}
