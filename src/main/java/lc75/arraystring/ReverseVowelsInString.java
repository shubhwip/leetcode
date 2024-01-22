package lc75.arraystring;

public class ReverseVowelsInString {

    // Works fine
    // But Additional Space Complexity of StringBuilder
    // Can be done in place
    public String reverseVowels(String s) {
        StringBuilder sb1 = new StringBuilder();
        for(int i=s.length()-1; i>=0; i--) {
            if(isVowel(s.charAt(i))) {
                sb1.append(s.charAt(i));
            }
        }
        StringBuilder sb2 = new StringBuilder(s);
        int p =0;
        for(int i=0; i<sb2.length(); i++) {
            if(isVowel(sb2.charAt(i))) {
                sb2.setCharAt(i, sb1.charAt(p));
                p++;
            }
        }
        return sb2.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' | c == 'e' | c == 'i' | c == 'o' | c == 'u'
                | c == 'A' | c == 'E' | c == 'I' | c == 'O' | c == 'U'
                ? true : false;
    }
}
