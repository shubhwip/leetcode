package medium.codewarkatas;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LargeFactorials {

    public static String Factorial(int n) {
        if(n < 0)
            return null;
        return multiply(new StringBuilder(String.valueOf(n)), n);
    }

    public String sayHello(String [] name, String city, String state){
        return new StringBuilder().append("Hello, ")
                .append(Arrays.stream(name).collect(Collectors.joining(" ")).toString())
                .append("! Welcome to ").append(city).append(", ").append(state).append("!").toString();
    }

    // Many String Builder Operations
    //
    public static String multiply(StringBuilder sb1, int n) {
        while(n>1) {
            StringBuilder sb2 = new StringBuilder();
            long rem = 0;
            long mod = 0;
            for(int i=sb1.length()-1; i>=0; i--) {
                long res = (sb1.charAt(i)-48)*(n-1) + rem;
                rem = res/10;
                mod = res%10;
                sb2.append(mod);
            }
            sb1.delete(0, sb1.length());
            if(rem != 0) {
                sb1.append(rem);
            }
            if(!sb2.toString().matches("\\[0\\]+")) {
                sb1.append(sb2.reverse());
            }
            n--;
        }
        return sb1.toString();

    }

    public static void main(String[] args) {
        System.out.println(Factorial(15));
    }
}
