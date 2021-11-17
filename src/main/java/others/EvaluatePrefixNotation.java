package others;

import java.util.Stack;

public class EvaluatePrefixNotation {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static int applyOperand(int a, int b, Character operator) {
        if (operator == '+')
            return a + b;
        else if (operator == '-')
            return a - b;
        else if (operator == '*')
            return a * b;
        else if (operator == '/')
            return a / b;
        else
            return 0;
    }

    public static int getResult(String expression) {
        Stack<String> s = new Stack<>();
        int res = 0;
        s.push(String.valueOf(expression.charAt(0)));
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' ||
                    expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' ||
                    expression.charAt(i) == '/') {
                if (!s.isEmpty() && isInteger(s.peek())) {
                    s.push("@");
                    s.push(String.valueOf(expression.charAt(i)));
                } else
                    s.push(String.valueOf(expression.charAt(i)));

            } else if (Character.isDigit(expression.charAt(i))) {
                if (!s.isEmpty() && (isInteger(s.peek()))) {
                    res = applyOperand(Integer.parseInt(s.pop()), expression.charAt(i) - '0', s.pop().charAt(0));
                    if (!s.isEmpty() && s.peek() == "@") {
                        while (!s.isEmpty() && s.peek() == "@") {
                            s.pop();
                            res = applyOperand(Integer.parseInt(s.pop()), res, s.pop().charAt(0));
                        }
                    }
                    s.push(String.valueOf(res));

                } else {
                    s.push(String.valueOf(expression.charAt(i)));
                }
            }
        }
        while (!s.isEmpty() && s.size() >= 3) {
            res = applyOperand(Integer.parseInt(s.pop()), Integer.parseInt(s.pop()), s.pop().charAt(0));
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getResult("+12"));
        System.out.println(getResult("*+123"));
        System.out.println(getResult("-*+1234"));
        System.out.println(getResult("+6*-4+238"));
        System.out.println(getResult("*-+646/+552"));
    }
}
