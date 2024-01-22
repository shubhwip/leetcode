package lc75.stack;

// https://leetcode.com/problems/removing-stars-from-a-string
public class RemovingStarsFromString {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        char[] sChars = s.toCharArray();
        for(char c : sChars) {
            if(c == '*') {
                sb.deleteCharAt(sb.length()-1);
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
