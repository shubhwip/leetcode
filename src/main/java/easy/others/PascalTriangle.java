package easy.others;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lp = new ArrayList<>();
        generateRec(numRows, lp);
        return lp;
    }

    public static void generateRec(int numRows, List<List<Integer>> lp) {
        List<Integer> l = new ArrayList<>(numRows);
        if (numRows == 1) {
            l.add(1);
        } else {
            generateRec(numRows - 1, lp);
            l.add(1);
            for (int i = 1; i < numRows - 1; i++) {
                l.add(lp.get(numRows - 2).get(i) + lp.get(numRows - 2).get(i - 1));
            }
            l.add(1);
        }
        lp.add(l);
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
