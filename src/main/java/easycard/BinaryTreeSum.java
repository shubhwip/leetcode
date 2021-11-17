package easycard;

// Binary Tree Represented As Array.
public class BinaryTreeSum {
    public static String solution(long[] arr) {
        if (arr.length == 0 || arr.length == 1)
            return "";
        long left = sum2(arr, 1);
        long right = sum2(arr, 2);
        if (left == right)
            return "";
        if (left > right)
            return "Left";
        else
            return "Right";
    }

    public static long sum2(long[] arr, int index) {
        long sum = 0;
        if (index > arr.length-1)
            return 0;
        else if (arr[index] == -1)
            return 0;
        else if (index < arr.length)
            sum += arr[index];
        sum += sum2(arr, 2 * index + 1) + sum2(arr, 2 * index + 2);
        return  sum;

    }

    public static void main(String[] args) {
        long arr[] = new long[]{1, 10, 5, 1, 0, 6};
        System.out.println(solution(arr));
    }

}
