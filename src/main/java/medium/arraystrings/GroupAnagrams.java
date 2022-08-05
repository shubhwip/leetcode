 package medium.arraystrings;

 import org.apache.commons.math3.util.Pair;

 import java.util.*;
 import java.util.stream.Collectors;

 public class GroupAnagrams {

     // Most Optimized Solution
     // Taken Help from Discuss Section
     // This solution is amazing
     public static List<List<String>> groupAnagrams(String[] strs) {
         List<List<String>> lp = new ArrayList<>();
         // This is space complexity here.
         Map<String, List<String>> map = new HashMap<>();
         for (String str : strs) {
             char[] ch = new char[26];
             for (int i = 0; i < str.length(); i++) {
                 ch[str.charAt(i) - 'a']++;
             }
             String arr = String.valueOf(ch);
             if (!map.containsKey(arr))
                 map.put(arr, new ArrayList<>());
             map.get(arr).add(str);
         }
         return map.values().stream().collect(Collectors.toList());
     }

     // Hashing
     // 113 / 114 test cases passed.
     public static List<List<String>> groupAnagrams5(String[] strs) {
         List<List<String>> lp = new ArrayList<>();
         int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
         long[][] m = new long[strs.length][1];
         for (int i = 0; i < strs.length; i++) {
             long result = 1;
             for (int j = 0; j < strs[i].length(); j++) {
                 int p = prime[(strs[i].charAt(j) - 'a')];
                 if (result != 0 && p > Long.MAX_VALUE / result) {
                     result = result / p;
                 }
                 result = result * prime[(strs[i].charAt(j) - 'a')];
             }
             m[i][0] = result;
         }
         for (int i = 0; i < strs.length; i++) {
             if (m[i][0] != 0) {
                 List<String> l = new ArrayList<>();
                 l.add(strs[i]);
                 for (int j = i + 1; j < strs.length; j++) {
                     if (m[j][0] != 0 && m[i][0] == m[j][0]) {
                         l.add(strs[j]);
                         m[j][0] = 0;
                     }
                 }
                 m[i][0] = 0;
                 if (!l.isEmpty())
                     lp.add(l);
             }
         }
         return lp;
     }

     // Minor improvment but no performance improvment
     public static List<List<String>> groupAnagrams4(String[] strs) {
         List<List<String>> lp = new ArrayList<>();
         int[][] arr = new int[strs.length][26];
         for (int i = 0; i < strs.length; i++) {
             int sum = 0;
             for (int j = 0; j < strs[i].length(); j++) {
                 arr[i][strs[i].charAt(j) - 97]++;
             }
         }
         boolean[] visited = new boolean[strs.length];
         for (int i = 0; i < strs.length; i++) {
             List<String> l = new ArrayList<>();
             l.add(strs[i]);
             if (!visited[i]) {
                 visited[i] = true;
                 for (int j = i + 1; j < strs.length; j++) {
                     boolean anagram = true;
                     if (!Arrays.equals(arr[i], arr[j]))
                         anagram = false;
                     if (anagram) {
                         l.add(strs[j]);
                         visited[j] = true;
                     }
                 }
                 lp.add(l);
             }

         }
         return lp;
     }

     // Can say correct solution but it is TLE obviously with this complexity
     // At this point 111 / 114 test cases passed.
     // Obviously needs amazing optimization here
     public static List<List<String>> groupAnagrams3(String[] strs) {
         List<List<String>> lp = new ArrayList<>();
         int[][] arr = new int[strs.length][26];
         for (int i = 0; i < strs.length; i++) {
             int sum = 0;
             for (int j = 0; j < strs[i].length(); j++) {
                 arr[i][strs[i].charAt(j) - 97]++;
             }
         }
         boolean[] visited = new boolean[strs.length];
         for (int i = 0; i < strs.length; i++) {
             List<String> l = new ArrayList<>();
             l.add(strs[i]);
             if (!visited[i]) {
                 visited[i] = true;
                 for (int j = i + 1; j < strs.length; j++) {
                     boolean anagram = true;
                     for (int k = 0; k < 26; k++) {
                         if (arr[i][k] != arr[j][k])
                             anagram = false;
                     }
                     if (anagram) {
                         l.add(strs[j]);
                         visited[j] = true;
                     }
                 }
                 lp.add(l);
             }

         }
         return lp;
     }


     // 45/114 Test cases passed
     // Summation approach won't work
     // Incorrect Solution completely because sum accidently become same for two number
     // for e.g. duh and ill
     public static List<List<String>> groupAnagrams2(String[] strs) {
         List<Pair<String, Integer>> lp = new ArrayList<>();
         for (int i = 0; i < strs.length; i++) {
             int sum = 0;
             for (int j = 0; j < strs[i].length(); j++) {
                 sum += strs[i].charAt(j) - '0';
             }
             lp.add(new Pair<>(strs[i], sum));
         }
         Map<Integer, List<String>> map = lp.stream().
                 collect(Collectors.groupingBy(x -> x.getValue(), Collectors.mapping(x -> x.getKey(), Collectors.toList())));
         return map.values().stream().collect(Collectors.toList());
     }

     // Below implementation passed 32/114 cases
     // This implementation will fail where there are same strings passed
     // Keys will be same and data will be replaced in the map
     public static List<List<String>> groupAnagrams1(String[] strs) {
         List<List<String>> ls = new ArrayList<>();
         Map<String, Integer> map = new HashMap<>();
         for (int i = 0; i < strs.length; i++) {
             int sum = 0;
             for (int j = 0; j < strs[i].length(); j++) {
                 sum += strs[i].charAt(j) - '0';
             }
             map.put(strs[i], sum);
         }
         Map<Integer, List<String>> map1 = map.entrySet().stream().collect(Collectors.
                 groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
         return map1.values().stream().collect(Collectors.toList());
     }

     public static void main(String[] args) {
         System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

         // failed test 1
 //        Input:
 //["",""]
 //        Output:
 //[[""]]
 //        Expected:
 //[["",""]]
         // 32/114 test cases passed

         //System.out.println(groupAnagrams(new String[]{"", ""}));

 //        Input:
 //["cab","tin","pew","duh","may","ill","buy","bar","max","doc"]
 //        Output:
 //[["buy"],["duh","ill"],["bar"],["doc"],["max"],["cab"],["may"],["tin"],["pew"]]
 //        Expected:
 //[["max"],["buy"],["doc"],["may"],["ill"],["duh"],["tin"],["bar"],["pew"],["cab"]]
         // failed test case 2 Implementation 2
         //System.out.println(groupAnagrams(new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"}));
         System.out.println(groupAnagrams(new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}));
     }
 }
