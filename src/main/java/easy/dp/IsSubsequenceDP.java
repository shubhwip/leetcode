package easy.dp;

public class IsSubsequenceDP {
    public static boolean isSubsequenceRecursive(String s, String t, int i) {
        if(s.isEmpty() || t.isEmpty())
            return false;
        else if(s.charAt(i) == t.charAt(i))
            return true;
        return isSubsequenceRecursive(s.substring(i+1, s.length()), t.substring(i+1, t.length()), i+1);
    }

    public static void main(String[] args) {
        System.out.println(isSubsequenceRecursive("abc", "ahbgdc", 0));
        System.out.println(isSubsequenceRecursive("aec", "abcde", 0));
    }
}
