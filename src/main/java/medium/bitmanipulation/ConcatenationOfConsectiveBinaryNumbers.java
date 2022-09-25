package medium.bitmanipulation;

import java.util.stream.IntStream;

public class ConcatenationOfConsectiveBinaryNumbers {

    static long mod = 1_000_000_007;
    public int concatenatedBinary(int n) {
        return concatenatedBinaryNaive(n);
    }

    public int concatenatedBinaryNaive(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=n; i>=1; i--) {
            sb.append(getBinaryRepresentation(n));
        }
        long k=0;
        long sum=0;
        //System.out.println(mod);
        //System.out.println(sb.toString().length());
        for(int i=sb.length()-1; i>=0; i--) {
            //System.out.println(num);
            if(sb.charAt(i) == '1') {
                long res = ((long) Math.pow(2, k));
                sum += res;
                sum %= mod;
            }
            k++;
        }
        return (int)sum;
    }

    public String getBinaryRepresentation(int n) {
        return Integer.toBinaryString(n);

    }
}
