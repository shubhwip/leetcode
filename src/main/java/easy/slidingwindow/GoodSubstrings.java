package easy.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class GoodSubstrings {

    public int countGoodSubstringsBF(String s) {

        int count = 0;

        for(int i=0;i<s.length()-2;i++){

            char c1 = s.charAt(i);
            char c2 = s.charAt(i+1);
            char c3 = s.charAt(i+2);

            if(c1!=c2 && c2!=c3 && c1!=c3){
                count++;
            }
        }

        return count;
    }

    public int countGoodSubstrings(String s) {
        int l=0,r=0;
        Map<Character, Integer> goodStrings = new HashMap<>();
        int goodSubstrings = 0;
        while(r<s.length()) {
            if(r-l == 3) {
                if(goodStrings.size() == 3)
                    goodSubstrings++;
                if(goodStrings.containsKey(s.charAt(l))) {
                    if(goodStrings.get(s.charAt(l)) == 1)
                        goodStrings.remove(s.charAt(l));
                    else
                        goodStrings.put(s.charAt(l), goodStrings.get(s.charAt(l)) - 1);
                }
                l++;
            }
            goodStrings.merge(s.charAt(r), 1, Integer::sum);
            r++;
        }
        if(r-l == 3) {
            if(goodStrings.size() == 3)
                goodSubstrings++;
        }
        return goodSubstrings;
    }
}
