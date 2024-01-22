package lc75.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CloseStrings {

    public boolean closeStrings(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        char[] word1Chars = word1.toCharArray();
        char[] word2Chars = word2.toCharArray();
        for(char c : word1Chars) {
            freq1[c-97]++;
        }
        for(char c : word2Chars) {
            freq2[c-97]++;
        }
        boolean result = true;
        for(int i=0;i<26;i++) {
            if(freq1[i] != 0 && freq2[i] == 0)
                return false;
            else if(freq2[i] != 0 && freq1[i] == 0) {
                return false;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<26;i++) {
            map.merge(i, freq1[i], Integer::sum);
            map.merge(i, -freq2[i], Integer::sum);
            if(map.get(i) != 0)
                return false;
        }

        return true;
    }
    public boolean closeStrings1(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        char[] word1Chars = word1.toCharArray();
        char[] word2Chars = word2.toCharArray();
        for(char c : word1Chars) {
            freq1[c-97]++;
        }
        for(char c : word2Chars) {
            freq2[c-97]++;
        }
        boolean result = true;
        for(int i=0;i<26;i++) {
            if(freq1[i] != 0 && freq2[i] == 0)
                return false;
            else if(freq2[i] != 0 && freq1[i] == 0) {
                return false;
            }
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0;i<26;i++) {
            if(freq1[i] != 0) {
                sb1.append(freq1[i]);
            }
            if(freq2[i] != 0) {
                sb2.append(freq2[i]);
            }
        }
        char[] chars1 = sb1.toString().toCharArray();
        Arrays.sort(chars1);
        String sorted1 = new String(chars1);
        char[] chars2 = sb2.toString().toCharArray();
        Arrays.sort(chars2);
        String sorted2 = new String(chars2);

        System.out.println(sorted1);
        System.out.println(sorted2);
        return sorted1.equals(sorted2) ? true : false;
    }
}
