package mediumcard.backtracking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateParantheses {

    static Set<String> par = new HashSet<>();

    // Slowest Solution Time Complexity
    // 936 ms 8/8 test cases passed
    public static List<String> generateParenthesis(int n) {
        if (n == 0)
            return par.stream().collect(Collectors.toList());
        else if (n == 1) {
            par.add("()");
            return par.stream().collect(Collectors.toList());
        }
        backtrack("()", n);
        return par.stream().collect(Collectors.toList());
    }

    private static void backtrack(String s, int n) {
        if (s.length() > 2 * n)
            return;
        else if (s.length() == 2 * n)
            par.add(s);
        for (int i = 0; i < s.length(); i++) {
            String p = s.substring(0, i) + "()" + s.substring(i, s.length());
            backtrack(p, n);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
