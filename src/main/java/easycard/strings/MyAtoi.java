package easycard.strings;

public class MyAtoi {
    public static int myAtoi(String s) {
        StringBuilder s1 = new StringBuilder();
        boolean intSeen = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            else if (s.charAt(i) == 43 || s.charAt(i) == 45) {
                if (i + 1 < s.length() && !(s.charAt(i + 1) < 58 && s.charAt(i + 1) > 47))
                    return 0;
                else if ((i + 1 < s.length() && i == 0) || (i > 1 && s.charAt(i - 1) == ' ') && i + 1 < s.length())
                    s1.append(s.charAt(i));
                else
                    return 0;
            } else if (s.charAt(i) < 58 && s.charAt(i) > 48) {
                intSeen = true;
                s1.append(s.charAt(i));
            } else if (s.charAt(i) == '0') {
                if (!intSeen)
                    continue;
                else
                    s1.append(s.charAt(i));
            } else if (s.charAt(i) == '.') {
                break;
            } else if (s.charAt(i) > 58 || s.charAt(i) < 48) {
                if (s1.length() == 1 && !(s1.charAt(0) < 58 && s1.charAt(0) > 48)) {
                    s1.deleteCharAt(0);
                }
                break;
            }
        }
        if (s1.toString().length() == 0)
            return 0;
        String s2 = s1.toString();
        String int_max = "2147483647";
        String int_min = "-2147483648";
        if (s2.startsWith("-")) {
            if (s2.length() > int_min.length()) {
                return Integer.MIN_VALUE;
            } else if (s2.length() == int_min.length()) {
                int comparison = s2.toString().compareTo(int_min);
                if (comparison > 0)
                    return Integer.MIN_VALUE;
            }
        } else {
            s2 = s2.startsWith("+") ? s2.substring(1, s2.length()) : s2;
            if (s2.length() > int_max.length()) {
                return Integer.MAX_VALUE;
            } else if (s2.length() == int_max.length()) {
                int comparison = s2.toString().compareTo(int_max);
                if (comparison > 0)
                    return Integer.MAX_VALUE;
            }
        }

        return Integer.parseInt(s2);
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("   +0 123"));
        System.out.println(myAtoi("    +0a32"));
        System.out.println(myAtoi("     +000145a"));
        System.out.println(myAtoi("       -000a12"));
        System.out.println(myAtoi("       -00110a12"));
        System.out.println(myAtoi("       -0010002kvjc"));
        System.out.println(myAtoi("-000000000000001"));
        System.out.println(myAtoi("+w21"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("0"));
        System.out.println(myAtoi("  0000000000012345678"));
        System.out.println(myAtoi("00000-42a1234"));
        System.out.println(myAtoi("36w.6979"));
        System.out.println(myAtoi("3w.6979"));
        System.out.println(myAtoi("-6w.6979"));
        System.out.println(myAtoi("-"));
        System.out.println(myAtoi("+"));
        System.out.println(myAtoi("-w"));
        System.out.println(myAtoi("3.1456"));
        System.out.println(myAtoi("+-21"));
        System.out.println(myAtoi("+-21.38"));
        System.out.println(myAtoi("--21"));
        System.out.println(myAtoi("+-21"));
        System.out.println(myAtoi("+91283472332.14159"));
        System.out.println(myAtoi("+91283472w32.14159"));
        System.out.println(myAtoi("+91283472332"));
        System.out.println(myAtoi("+987"));
        System.out.println(myAtoi("-42"));
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("-91283472332"));

    }
}
