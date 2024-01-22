package lc75.arraystring;

public class MergeAlternatively {
    // My solution
    // Can be further optimised without using substring functions
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int word1Len = word1.length();
        int word2Len = word2.length();
        int minLen = Integer.min(word1Len, word2Len);
        for(int i=0;i<minLen;i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        // can be replaced with sb.append(word1, minLen, word1Len);
        sb.append(word1.substring(minLen, word1Len));
        sb.append(word2.substring(minLen, word2Len));
        return sb.toString();
    }

    public String mergeAlternatelyOptimised(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int word1Len = word1.length();
        int word2Len = word2.length();
        for(int i=0; i<word1Len || i<word2Len; i++) {
            if(i<word1Len) {
                sb.append(word1.charAt(i));
            }
            if(i<word2Len) {
                sb.append(word2.charAt(i));
            }
        }
        return sb.toString();
    }
}
