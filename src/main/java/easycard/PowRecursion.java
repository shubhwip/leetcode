package easycard;

public class PowRecursion {
    // Correct Answer but StackOverflow error
//     public double myPow(double x, int n) {
//         return n<0 ? (double)1/myPowR(x,(-1)*n) : myPowR(x,n);
//     }

//     public double myPowR(double x, int n) {
//         if(n == 0)
//             return 1;
//         else
//             return x*myPowR(x, n-1);

//     }

    // Again Stackoverflow error (291/304 test cases passed)
//    public static double myPow(double x, int n) {
//        double res = 1.0;
//        return n < 0 ? (double) 1 / myPowR(x, (-1) * n, res) : myPowR(x, n, res);
//    }
//
//    public static double myPowR(double x, int n, double res) {
//        if (n == 0)
//            return res;
//        else
//            return myPowR(x, n - 1, (double) x * res);
//    }

    // Accepted
    public static double myPow(double x, int n) {
        double res = 1.0;
        return n < 0 ? (double) 1 / myPowR(x, (-1) * n, res) : myPowR(x, n, res);
    }

    // There are subproblems here associated.
    public static double myPowR(double x, int n, double res) {
        if (n == 0)
            return res;
        // calculate subproblem recursively
        double pow = myPowR(x, n / 2, res);

        if ((n & 1) == 1) { // if `y` is odd
            return x * pow * pow;
        }
        // otherwise, `y` is even
        return pow * pow;
    }


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(myPow(2.00000, 10));
        System.out.println(myPow(0.00001, 2147483647));
    }
}
