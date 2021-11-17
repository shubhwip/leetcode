package mediumcard.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfPhoneNumber {

    public static List<String> letters = new ArrayList<>();
    public static Map<Character, String> map = new HashMap<>();

    public static List<String> letterCombinations(String digits) {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backtrack(digits, "", 0);
        return letters;
    }

    private static void backtrack(String digits, String s, int p) {
        if (s.length() > digits.length())
            return;
        else if (s.length() == digits.length())
            letters.add(s);
        for (int i = p; i < digits.length(); i++) {
            String ab = map.get(digits.charAt(i));
            for (int j = 0; j < ab.length(); j++) {
                backtrack(digits, s + ab.charAt(j), i + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("2345678"));
    }
}
