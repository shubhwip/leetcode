package others;

import java.util.*;

// Tessian
public class EvaluatePrefixNotationWithVariables {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static Integer applyOperation(int a, int b, Character operator) {
        if (operator == '+')
            return a + b;
        else if (operator == '-')
            return a - b;
        else if (operator == '*')
            return a * b;
        else if (operator == '/')
            return a / b;
        else
            return null;
    }

    public static boolean isOperationValid(Character operator) {
        return (operator == '+' || operator == '-' || operator == '*' || operator == '/') ? true : false;
    }


    public static int evaluatePrefixNotation(String expression, Map<Character, List<Integer>> map) {
        List<String> expressions = new ArrayList<>();
        expressions.add(expression);
        for (Map.Entry<Character, List<Integer>> en : map.entrySet()) {
            List<String> temp = new ArrayList<>();
            int listSize = en.getValue().size();
            if (listSize == 2) {
                for (String s : expressions) {
                    int low = en.getValue().get(0);
                    int high = en.getValue().get(listSize - 1);
                    for (int p = low; p < high; p++) {
                        temp.add(s.replace(en.getKey(), Character.forDigit(p, 10)));
                    }
                }
            } else if (listSize == 1) {
                for (String s : expressions) {
                    temp.add(s.replace(en.getKey(), Character.forDigit(en.getValue().get(0), 10)));
                }
            }
            expressions.clear();
            expressions.addAll(temp);
        }
        System.out.println(expressions);
        int max = Integer.MIN_VALUE;
        for (String exp : expressions) {
            int res = getResult(exp);
            if (res > max) {
                max = res;
            }
        }
        return max;
    }


    public static Integer apply(int operand, Stack<String> s) {
        int num = Integer.parseInt(s.pop());
        char operation = s.pop().charAt(0);
        if (!isOperationValid(operation))
            return null;
        return applyOperation(num, operand, operation);
    }

    public static Integer getResult(String expression) {
        Stack<String> s = new Stack<>();
        Integer res = 0;
        s.push(String.valueOf(expression.charAt(0)));
        for (int i = 1; i < expression.length(); i++) {
            if (isOperationValid(expression.charAt(i))) {
                if (!s.isEmpty() && isInteger(s.peek())) {
                    s.push("@");
                }
                s.push(String.valueOf(expression.charAt(i)));
            } else if (Character.isDigit(expression.charAt(i))) {
                if (!s.isEmpty() && s.size() >= 2 && (isInteger(s.peek()))) {
                    res = apply(expression.charAt(i) - '0', s);
                    if (res == null)
                        return null;
                    if (!s.isEmpty() && s.peek() == "@") {
                        while (!s.isEmpty() && s.peek() == "@") {
                            s.pop();
                            res = apply(res, s);
                            if (res == null)
                                return null;
                        }
                    }
                    s.push(String.valueOf(res));

                } else {
                    s.push(String.valueOf(expression.charAt(i)));
                }
            }
        }
        while (!s.isEmpty() && s.size() >= 3) {
            res = applyOperation(Integer.parseInt(s.pop()), Integer.parseInt(s.pop()), s.pop().charAt(0));
            if (res == null)
                return null;
        }
        if (!s.isEmpty() && s.size() > 1)
            return null;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getResult("+12"));
        System.out.println(getResult("+1"));
        System.out.println(getResult("+123"));
        System.out.println(getResult("*+123"));
        System.out.println(getResult("-*+1234"));
        System.out.println(getResult("+6*-4+238"));
        System.out.println(getResult("*-+646/+552"));
        Map<Character, List<Integer>> map = new HashMap<>();
        map.put('x', Arrays.asList(0, 7));
        map.put('y', Arrays.asList(2, 6));
        System.out.println(evaluatePrefixNotation("*-+x46/+5y2", map));

//        map.put('x', Arrays.asList(1));
//        map.put('y', Arrays.asList(3));
//        System.out.println(evaluatePrefixNotation("*+2xy", map));
    }
}
