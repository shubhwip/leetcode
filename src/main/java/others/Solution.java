package others;

import java.util.*;


class Result {

    /*
     * Complete the 'programmerStrings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    public static boolean isProgrammerString(Map<Character, Integer> map) {
        String p = "programmer";
        Map<Character, Integer> fixedMap = new HashMap<>();
        fixedMap.put('p', 1);
        fixedMap.put('r', 3);
        fixedMap.put('o', 1);
        fixedMap.put('g', 1);
        fixedMap.put('a', 1);
        fixedMap.put('m', 2);
        fixedMap.put('e', 1);
        System.out.println(map);
        for (int i = 0; i < p.length(); i++) {
            if (map.get(p.charAt(i)) == null && fixedMap.get(p.charAt(i)) != map.get(p.charAt(i)))
                return false;
        }
        return true;
    }

    public static int findPosFromBeginning(int i, String s) {
        Map<Character, Integer> map = new HashMap<>();
        int j = i;
        for (j = i; i < s.length(); j++) {
            map.merge(s.charAt(j), 1, Integer::sum);
            if (j > 8 && isProgrammerString(map)) {
                System.out.println(j);
                System.out.println(map);
                break;
            }
        }
        return j;
    }

    // progxrammerrxproxgrammer 3 2
    // xprogxrmaxemrppprmmograeiruu 5 2
    // programmerprogrammer -1 0

    // creunmnyzmosubijualwxjwswdzahqeevhpxvzlsvxjkknljifoyinblwnvcmzqowhkokwdjbimldnvqaaeovdzwzqtnhqmhtgaerqpcimlzysnwzftsoqaclhajhkyvozbivmhwcevkxrkmtaaxhwfwoybfbsftjujnivqsncuhjlqohbpyxmmqjcueebhavdyhzkbgzpgctjlgtyywptvkhrdtlrwouqyxmvlhdnynnlcajrjpjnvroqvxzavobnawwfkoornzmmvqmrcclvdeyqskdhafxcttmjsjlthmkbwueavuukdkfahnyankqehdfxysnnpwfbtstkpgspkcuhwgxaugbngclqtybxcuksnvvrqvbbjyrgyqnggitrmfsnxkeospesapgfcmjaysuphoxnorajwxuvcpuorsasbjnpwcsandjwfzzjtsjpdeyigyzdfozuhlctkgiysqauiqninnwurwjklfnznmvoadcezafeckmhytfiorlbffkkarirrkwolvavjaddvjytcorijbmbavcuvkxdreupgskweiwyssqcvktpvtkuggdtlubtoqcvnmaagureliuwtorzzwqevmhuxfyoyzuacjmgdrnsdlozuptyptrnzyizjaiaaqrkosmwfqygoecwcbtdgrpqmzotbsbtcdpqtmgciwqyzkwfpiswtmfebgjduutpfnclwnqxdegjlohewlnuqcvncndtextmqadnydwgbenhbfghkmktfabwwhqnktwfynzolwvcjrcbpdfldjoktcxxawipkbbuoirxqslolqpwzsuvnlsqxnhukivpynbkbjubjriioimbzyprdfbmaqzgevdgwcqjcjpapiobgiwkqrobnijuaxakqlcuocfwhfqwzvzopozrazfakhzxzoupittagyxsguizbhkajizmxfcogjjuzoxzkuwcgklevoaywnhadeyodeccmaksbyxawbhqdb
    //820 715
            //99840 99814
    public static int findPosFromLast(int i, String s) {
        Map<Character, Integer> map = new HashMap<>();
        int j = i;
        for (j = i; j >= 0; j--) {
            map.merge(s.charAt(j), 1, Integer::sum);
            if ((i-j > 8) && isProgrammerString(map)) {
                System.out.println("L" + map);
                break;
            }
        }
        return j;
    }

    public static int programmerStrings(String s) {
        // Write your code here
        // One pass from the beginning
        int p = findPosFromBeginning(0, s);
        System.out.println(p);
        int q = findPosFromLast(s.length() - 1, s);
        System.out.println(q);
        return q - p - 1;
        // Second pass from the last
    }

    public static void main(String[] args) {
        // 2
        //System.out.println(programmerStrings("progxrammerrxproxgrammer"));
        List<Integer> l = Arrays.asList(1,4,6,2,8);
        Comparator<Integer> c = getCom(l);
        l.sort(c);
        System.out.println(l);
    }


    private static Comparator<Integer> getCom(List<Integer> l) {
        return Comparator.comparingInt(a -> -a);
    }

}

