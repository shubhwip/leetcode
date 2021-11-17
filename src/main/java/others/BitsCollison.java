package others;

// Babylon
public class BitsCollison {


    public static int getCollisonsOptimizedRecursion(String arr, int i, int count, int sum) {
        if (i > arr.length() - 1)
            return sum;
        if (arr.charAt(i) == '0') {
            count++;
        } else if (arr.charAt(i) == '1') {
            sum += count;
        }
        return getCollisonsOptimizedRecursion(arr, i + 1, count, sum);
    }

    public static int getCollisonsOptimized(String arr) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.charAt(i) == '0') {
                count++;
            } else if (arr.charAt(i) == '1') {
                sum += count;
            }
        }
        return sum;
    }

    public static int getCollisons(String arr) {
        int sum = 0;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.charAt(i) == '0') {
                for (int j = i + 1; j < arr.length(); j++) {
                    if (arr.charAt(j) == '1')
                        sum++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String str = "01101001010101010111100000100010010010001000100100011010011001010010101001001001001001001000100100100100100010001000";
        System.out.println(getCollisons(str));
        System.out.println(getCollisonsOptimized(str));
        System.out.println(getCollisonsOptimizedRecursion(str, 0, 0, 0));
    }
}
