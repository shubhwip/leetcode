package lc75.arraystring;

import java.util.ArrayList;
import java.util.List;

public class GreatestCommonDivisorOfStrings {

    // First Attempt with Two Passes
    // Brute force solution
    // Checked hint for solving this problem
    // 31 ms
    //Beats
    //5.03%
    //of users with Java
    //45.19
    //MB
    //Beats
    //5.61%
    //of users with Java
    public static String gcdOfStrings(String str1, String str2) {
        String result = "";
        int str1Len = str1.length();
        int str2Len = str2.length();
        int min = Integer.min(str1Len, str2Len);
        int maxLen = Integer.MIN_VALUE;
        List<Integer> l = new ArrayList<>();
        // Optimisation Possible here
        // We can exit early and optimise code
        // By starting with max sized substring
        // for(int i=min; i>=1; i--) {
        for(int i=1; i<=min; i++) {
            String substr = str1.substring(0, i);
            int j=0;
            while(j < str1Len) {
                if(str1.substring(j, Integer.min(str1Len, j+substr.length())).indexOf(substr) != 0)
                    break;
                j+=substr.length();
            }
            int k=0;
            while(k < str2Len) {
                if(str2.substring(k, Integer.min(str2Len, k+substr.length())).indexOf(substr) != 0)
                    break;
                k+=substr.length();
            }
            if(j == str1Len && k == str2Len) {
                // Logic added after 1st failure.
                // There can be maximum string as well.
                result = substr;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(gcdOfStrings("ABCABC", "ABC"));
//        System.out.println(gcdOfStrings("ABABAB", "ABAB"));
//        System.out.println(gcdOfStrings("ABAABAABA", "ABA"));
//        System.out.println(gcdOfStrings("ABAABAABC", "ABA"));
//        System.out.println(gcdOfStrings("ABCDEF", "ABC"));
        System.out.println(gcdOfStrings("ABABABAB", "ABAB"));
    }
}
