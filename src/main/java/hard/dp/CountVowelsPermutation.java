package hard.dp;

import java.util.Arrays;

public class CountVowelsPermutation {
    static int mod = 1000000000 + 7;
    static int[][] memo;

    // Solved HARD DP problem completely myself
    // Memoization
    public static int countVowelPermutation(int n) {

        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        memo = new int[n + 1][26];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return countVowelPermutationMemo(n, vowels, 'p');

    }


    // 19 / 43 test cases passed.
    public static int countVowelPermutationMemo(int n, char[] vowels, char curr) {

        if (n == 0) {
            return 1;
        }
        if (memo[n][curr - 97] != -1)
            return memo[n][curr - 97] % mod;
        int count = 0;

        if (curr == 'p') {
            for (char c : vowels) {
                count += (countVowelPermutationMemo(n - 1, vowels, c));
                count %= mod;
            }
        }

        if (curr == 'a') {
            count += (countVowelPermutationMemo(n - 1, vowels, 'e'));
            count %= mod;
        }
        if (curr == 'e') {
            count += (countVowelPermutationMemo(n - 1, vowels, 'a'));
            count %= mod;
            count += (countVowelPermutationMemo(n - 1, vowels, 'i'));
            count %= mod;
        }
        if (curr == 'o') {
            count += (countVowelPermutationMemo(n - 1, vowels, 'i'));
            count %= mod;
            count += (countVowelPermutationMemo(n - 1, vowels, 'u'));
            count %= mod;
        }
        if (curr == 'u') {
            count += (countVowelPermutationMemo(n - 1, vowels, 'a'));
            count %= mod;
        }

        if (curr == 'i') {
            for (char c : vowels) {
                if (curr != 'p' && curr != c) {
                    count += (countVowelPermutationMemo(n - 1, vowels, c));
                    count %= mod;
                }
            }
        }

        return memo[n][curr - 97] = count % mod;
    }

    public static int countVowelPermutationRecursion(int n, char[] vowels, char curr) {

        if (n == 0) {
            return 1;
        }
        int count = 0;

        if (curr == 'p') {
            for (char c : vowels) {
                count += (countVowelPermutationRecursion(n - 1, vowels, c));
                count %= mod;
            }
        }

        if (curr == 'a') {
            count += (countVowelPermutationRecursion(n - 1, vowels, 'e'));
            count %= mod;
        }
        if (curr == 'e') {
            count += (countVowelPermutationRecursion(n - 1, vowels, 'a'));
            count %= mod;
            count += (countVowelPermutationRecursion(n - 1, vowels, 'i'));
            count %= mod;
        }
        if (curr == 'o') {
            count += (countVowelPermutationRecursion(n - 1, vowels, 'i'));
            count %= mod;
            count += (countVowelPermutationRecursion(n - 1, vowels, 'u'));
            count %= mod;
        }
        if (curr == 'u') {
            count += (countVowelPermutationRecursion(n - 1, vowels, 'a'));
            count %= mod;
        }

        if (curr == 'i') {
            for (char c : vowels) {
                if (curr != 'p' && curr != c) {
                    count += (countVowelPermutationRecursion(n - 1, vowels, c));
                    count %= mod;
                }
            }
        }

        return count % mod;
    }

    public static void main(String[] args) {
        System.out.println(countVowelPermutation(50));
    }
}
