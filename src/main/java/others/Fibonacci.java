package others;

public class Fibonacci {

    public static int fibIter(int n) {
        if (n == 1 || n == 2)
            return n - 1;
        int a = 0;
        int b = 1;
        int i = 3;
        int c = 0;
        while (i <= n) {
            c = a + b;
            a = b;
            b = c;
            i++;
        }
        return c;
    }

    public static int fibRec(int n) {
        if (n == 1 || n == 2)
            return n - 1;
        return fibRec(n - 1) + fibRec(n - 2);
    }


    public static void main(String[] args) {
        System.out.println(fibIter(10));
        System.out.println(fibRec(1600));
    }
}
