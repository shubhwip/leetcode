package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void lcs(String a, String b, int x, int y, StringBuilder sb) {
        if (a.charAt(x) == b.charAt(y) && sb.toString().isEmpty()) {
            sb.append(a.charAt(x));
        } else if (a.charAt(x) == b.charAt(y) && a.charAt(x - 1) == b.charAt(y - 1)) {
            sb.append(a.charAt(x));
        }
        for (int i = 0; y < b.length() - 1 && i < a.length() - 1; i++) {
            lcs(a, b, i, y + 1, sb);
        }
        for (int j = 0; x < a.length() - 1 && j < b.length() - 1; j++) {
            lcs(a, b, x + 1, j, sb);
        }
    }

    public static void lcsIterative(String a, String b, int x, int y, StringBuilder sb) {
        int maxLen = Integer.MIN_VALUE;
        for (int i = x; i < a.length(); i++) {
            for (int j = i + 1; j < a.length(); j++) {
                String s = a.substring(i, j + 1);
                if (b.contains(s) && maxLen < s.length()) {
                    maxLen = s.length();
                    sb.delete(0, sb.length());
                    sb.append(s);
                }
//                if (i < a.length() && a.charAt(i) == b.charAt(j)) {
//                    if (sb.toString().isEmpty()) {
//                        sb.append(a.charAt(i));
//                        i++;
//                    } else if (a.charAt(i - 1) == sb.toString().charAt(sb.length() - 1) && b.charAt(j - 1) == sb.toString().charAt(sb.length() - 1)) {
//                        sb.append(a.charAt(i));
//                        i++;
//                    } else {
//                        sb.delete(0, sb.length());
//                        sb.append(a.charAt(i));
//                        i++;
//                    }
//                }
            }
        }
        System.out.println(maxLen);
    }

    public static String lcsarr(String arr[]) {
        StringBuilder sb = new StringBuilder();
        int maxLen = Integer.MIN_VALUE;
        int minLen = Integer.MAX_VALUE;
        if (arr == null || arr.length == 0)
            return "";
        StringBuilder str = new StringBuilder(arr[0]);
        for (int p = 1; p < arr.length; p++) {
            if (minLen > arr[p].length()) {
                minLen = arr[p].length();
                str.delete(0, str.length());
                str.append(arr[p]);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                String substr = str.substring(i, j + 1);
                int s = 0;
                for (s = 0; s < arr.length; s++) {
                    if (!arr[s].contains(substr))
                        break;
                }
                if (s == arr.length && maxLen < substr.length()) {
                    maxLen = substr.length();
                    sb.delete(0, sb.length());
                    sb.append(substr);
                }
            }
//            if (maxLen == str.length())
//                break;
        }
        return sb.toString();
    }

    public static int[][] sumSubArray(int[] arr) {
        int[][] split = new int[2][];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            return new int[0][0];
        }
        int half = sum / 2;
        split[0] = new int[arr.length];
        int arr1Index = 0;
        for (arr1Index = 0; arr1Index < arr.length; arr1Index++) {
            sum = sum - arr[arr1Index];
            split[0][arr1Index] = arr[arr1Index];
            if (sum == half)
                break;
        }
        int arr2Index = arr1Index + 1;

        if (arr2Index >= arr.length)
            return new int[0][0];

        int j = 0;
        split[1] = new int[arr.length];
        for (j = 0; j < arr.length; j++) {
            sum = sum - arr[arr2Index];
            split[1][j] = arr[arr2Index];
            if (sum == 0 || sum < 0)
                break;
            arr2Index++;
        }
        if ((arr2Index + 1) != arr.length)
            return new int[0][0];
        return split;
    }

    public static void main(String[] args) {
        //StringBuilder sb = new StringBuilder();
        //lcsIterative("jay", "jayesh", 0, 0, sb);
        //lcsIterative("malgrace", "disgraceful", 0, 0, sb);
        //System.out.println(sb.toString());
        //String arr[] = new String[]{"gracefully", "disgrace", "disgraceful", "grace"};
        //System.out.println(lcsarr(arr));

        int intarr[] = new int[]{1, 4, 2, 3};
        int intarr1[] = new int[]{1, 4, 2, 3, 1};
        int intarr2[] = new int[]{4, 3, 2, 1};
        int intarr3[] = new int[]{1, 2, 3, 4, 5, 5};
        Stream.of(sumSubArray(intarr)).map(Arrays::toString).forEach(System.out::println);
        Stream.of(sumSubArray(intarr1)).map(Arrays::toString).forEach(System.out::println);
        Stream.of(sumSubArray(intarr2)).map(Arrays::toString).forEach(System.out::println);
        Stream.of(sumSubArray(intarr3)).map(Arrays::toString).forEach(System.out::println);

        List<List<Integer>> traversal = new ArrayList<>();
        List<Double> res = traversal.stream()
                .map(c -> c.stream().mapToDouble(a -> a).average().getAsDouble())
                .collect(Collectors.toList());
    }
}
