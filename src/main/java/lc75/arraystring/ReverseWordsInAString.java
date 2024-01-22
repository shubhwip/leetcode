package lc75.arraystring;

public class ReverseWordsInAString {

    // Works well
    // Additional Space Complexity
    // Can be done In Place
    public static String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=arr.length-1; i>0; i--) {
            sb.append(arr[i].trim() + " ");
        }
        sb.append(arr[0].trim());
        return sb.toString();
    }

    public static void main(String[] args) {
        reverseWords("  hello world  ");
    }
}
