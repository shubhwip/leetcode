package lc75.arraystring;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringCompression {
    // 31/76 test cases passed.
    // Didn't read problem statement correctly
    public int compress1(char[] chars) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char a : chars) {
            map.merge(a, 1, Integer::sum);
        }
        int len = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) > 1) {
                chars[len] = key;
                len++;
                char[] keysArr = map.get(key).toString().toCharArray();
                for (char c : keysArr) {
                    chars[len] = c;
                    len++;
                }

            } else {
                chars[len] = key;
                len++;
            }
        }
        return len;
    }

    public int compress(char[] chars) {
        int len = 0;
        int cnt = 1;
        int start = 1;
        int k = start;
        for (int i = start; i < chars.length; i++) {
            if (chars[i] == chars[i - 1])
                cnt++;
            else {
                chars[len] = chars[i - 1];
                len++;
                if (cnt > 1) {
                    char[] keysArr = String.valueOf(cnt).toCharArray();
                    for (char c : keysArr) {
                        chars[len] = c;
                        len++;
                    }
                }
                cnt = 1;
            }
        }
        chars[len] = chars[chars.length - 1];
        len++;
        if (cnt > 1) {
            char[] keysArr = String.valueOf(cnt).toCharArray();
            for (char c : keysArr) {
                chars[len] = c;
                len++;
            }
        }
        return len;
    }
}
