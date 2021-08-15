package weeklycontest254;

public class SubStringsInString {

    public static int numOfStrings(String[] patterns, String word) {
        int num = 0;
        for (int i = 0; i < patterns.length; i++) {
            if (isSubString(word, patterns[i])) {
                num++;
            }
        }
        return num;
    }

    public static boolean isSubString(String s1, String s2) {
        int counter = 0; //pointing s2
        int i = 0;
        for (; i < s1.length(); i++) {
            if (counter == s2.length())
                break;
            if (s2.charAt(counter) == s1.charAt(i)) {
                counter++;
            } else {
                //Special case where character preceding the i'th character is duplicate
                if (counter > 0) {
                    i -= counter;
                }
                counter = 0;
            }
        }
        return counter < s2.length() ? false : true;
    }

    public static boolean isSubString3(String word, String substr) {
        int p = 0;
        int q = 0;
        if (substr.isEmpty()) {
            return true;
        } else if (substr.length() > word.length()) {
            return false;
        }
        boolean mod = false;
        for (int j = 0; j < word.length(); j++) {
            if (substr.charAt(0) == word.charAt(j)) {
                q = j;
                mod = true;
                break;
            }
        }
        if (!mod) {
            return false;
        }
        while (p < substr.length() && q < word.length()) {
            if (substr.charAt(p) != word.charAt(q)) {
                return false;
            }
            p++;
            q++;
        }
        if (p < substr.length())
            return false;
        return true;
    }

    public static boolean isSubString2(String word, String substr) {
        int p = 0;
        int q = 0;
        if (substr.isEmpty()) {
            return true;
        } else if (substr.length() > word.length()) {
            return false;
        }
        boolean mod = false;
        for (int i = 0; i < substr.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (substr.charAt(i) == word.charAt(j)) {
                    p = i;
                    q = j;
                    mod = true;
                    break;
                }
            }
            if (mod)
                break;
        }
        while (p < substr.length() && q < word.length()) {
            if (substr.charAt(p) != word.charAt(q)) {
                return false;
            }
            p++;
            q++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(numOfStrings(new String[]{"a", "b", "c"}, "aaaaabbbbb"));
        // Expected 0
        System.out.println(numOfStrings(new String[]{"vjwxinx"}, "ygwjtsmsvj"));
        System.out.println(numOfStrings(new String[]{"dgvyb", "j", "rc", "b", "gve", "rkbzd", "i", "ial", "tws", "c", "z"}, "zcobp"));
//        ["nmksysm","gwjt","vjwxinx","wj","yspxxcv","tpeud","sv","sztjxnrb","smsv","vj","pj","tsmsvj","e","svj","w","vj","awofrm","rshgiiyi","v"]
//        "ygwjtsmsvj"
    }
}
