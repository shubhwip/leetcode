package easycard;

public class KthSymbolGrammerRecursive {

    public static int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        if (K % 2 == 0) {
            if (kthGrammar(N - 1, K / 2) == 0) return 1;
            else return 0;
        } else {
            if (kthGrammar(N - 1, (K + 1) / 2) == 0) return 0;
            else return 1;
        }
    }

    public static int kthGrammarR(int n, int k) {
        StringBuilder sb = new StringBuilder();
        return kthGrammarRecursive(n).charAt(k - 1) - '0';
    }

    // Got MLE Issue With below code
    // 30
    //434991989
    // 15/55 cases passed only
    public static StringBuilder kthGrammarRecursive(int n) {
        if (n == 1)
            return new StringBuilder("0");
        StringBuilder sbp = kthGrammarRecursive(n - 1);
        StringBuilder sbnew = new StringBuilder();
        for (int i = 0; i < sbp.length(); i++) {
            if (sbp.charAt(i) == '0')
                sbnew.append("01");
            else
                sbnew.append("10");
        }
        return sbnew;
    }

    public static void main(String[] args) {
        System.out.println(kthGrammar(3, 3));
        System.out.println(kthGrammar(30, 434991989));
    }
}
