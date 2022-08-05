package easy.strings;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> s1 = new HashMap<>();
        Map<Character, Integer> t1 = new HashMap<>();

        for(Character c : s.toCharArray()) {
            s1.merge(c, 1, Integer::sum);
        }

        for(Character c : t.toCharArray()) {
            t1.merge(c, 1, Integer::sum);
        }

        // Missed this case 1st time
        if(s1.size() != t1.size())
            return false;

        for(Character c : s1.keySet()) {
            if(s1.get(c) != t1.get(c))
                return false;
        }

        return true;
    }
}
