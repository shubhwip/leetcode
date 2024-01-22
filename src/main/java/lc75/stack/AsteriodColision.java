package lc75.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AsteriodColision {
    // 142 / 275 testcases passed
    // 181 / 275 testcases passed
    // 236 / 275 testcases passed
    public static int[] asteroidCollision(int[] asteroids) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        for(int n : asteroids) {
            s.add(n);
        }
        while(!s.empty()) {
            if(s.size() == 1) {
                result.add(s.pop());
                break;
            }
            int n1 = s.pop();
            int n2 = s.peek();
            if(n1 > 0 && n2 > 0) {
                result.add(n1);
                // result.add(n2);
            } else if(n1 < 0 && n2 < 0) {
                result.add(n1);
                // result.add(n2);
            } else if(n1 > 0 && n2 < 0) {
                result.add(n1);
                // result.add(n2);
            }
            else {
                s.pop();
                if(Math.abs(n1) > Math.abs(n2))
                    s.add(n1);
                else if(Math.abs(n1) < Math.abs(n2))
                    s.add(n2);
            }
        }
        Collections.reverse(result);
        return result.stream().mapToInt(a->a).toArray();
    }

    public static void main(String[] args) {
        System.out.println(asteroidCollision(new int[]{-2,2,-1,-2}));
    }
}
