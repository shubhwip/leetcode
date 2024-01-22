package lc75.twopointers;

public class IsSubSequence {
    public static boolean isSubsequence(String s, String t) {
        return isSubsequence2P(s, t, 0, 0);
    }

    public static boolean isSubsequence2P(String s, String t, int i, int j) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen == 0)
            return true;
        int match = 0;
        while(i<sLen && j<tLen) {
            if(s.charAt(i) == t.charAt(j)) {
                match++;
                i++;
                j++;
            } else {
                j++;
            }
            if(match == sLen) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSub(String s, String t, int i, int j) {
        if(i == s.length()) {
            if(j >= s.length()) {
                return true;
            } else {
                return false;
            }
        }
        else if(j == t.length()) {
            if(i >= t.length()) {
                return true;
            } else {
                return false;
            }
        }

        if(s.charAt(i) == t.charAt(j)) {
            return isSub(s, t, i+1, j+1);
        }
        int p = i,q=j;
        return isSub(s, t, p, q+1) || isSub(s, t, p+1, q);
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("bcd", "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuubcd"));
    }
}
