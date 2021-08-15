package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    private final static String corpus = "Mary had a little lamb its fleece was white as snow;\n" +
            "And everywhere that Mary went, the lamb was sure to go.\n" +
            "It followed her to school one day, which was against the rule;\n" +
            "It made the children laugh and play, to see a lamb at school.\n" +
            "And so the teacher turned it out, but still it lingered near,\n" +
            "And waited patiently about till Mary did appear.\n" +
            "\"Why does the lamb love Mary so?\" the eager children cry;\"Why, Mary loves the lamb, you know\" the teacher did reply.\"";

    private static Map<String, Double> cumulativeDistributionFunction(Map<String, Integer> nextWords) {
        Map<String, Double> cdf = new LinkedHashMap<>();
        int sumVals = nextWords.values().stream().reduce(Integer::sum).orElse(0);
        for (Map.Entry<String, Integer> a : nextWords.entrySet()) {
            BigDecimal probablity = BigDecimal.valueOf(a.getValue()).divide(BigDecimal.valueOf(sumVals), MathContext.DECIMAL32).setScale(3);
            cdf.put(a.getKey(), probablity.doubleValue());
        }
        return cdf;
    }

    private static Map<String, Integer> nextWordsFrequency(int n, String text, String input) {
        Map<String, Integer> nextWords = new LinkedHashMap<>();
        String[] words = input.split("\\s");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("^[^a-zA-Z0-9\\s]+|[^a-zA-Z0-9\\s]+$", "");
        }
        for (int i = 0; i < words.length - (n - 1); i++) {
            if (words[i].equals(text)) {
                String separator = "";
                StringBuilder sb = new StringBuilder();
                for (int j = 1; j < n; j++) {
                    sb.append(separator + words[i + j]);
                    separator = " ";
                }
                nextWords.merge(sb.toString(), 1, Integer::sum);
            }
        }
        return nextWords;
    }

    /**
     * Comparator to compare Values first and then key
     *
     * @param <K>
     * @param <V>
     */
    static class ValueThenKeyComparator<K extends Comparable<? super K>,
            V extends Comparable<? super V>>
            implements Comparator<Map.Entry<K, V>> {

        public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
            int cmp1 = b.getValue().compareTo(a.getValue());
            if (cmp1 != 0) {
                return cmp1;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        }
    }

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            String[] userInputs = line.split(",");
            String inputText = userInputs[1];
            Integer ngramLength = Integer.valueOf(userInputs[0]);
            Map<String, Integer> nextWords = nextWordsFrequency(ngramLength, inputText, corpus);
            Map<String, Double> probs = cumulativeDistributionFunction(nextWords);
            List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(probs.entrySet());
            Collections.sort(list, new ValueThenKeyComparator<String, Double>());
            String separator = "";  // separator here is your ";"
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Double> a : list) {
                sb.append(separator).append(a.getKey() + "," + String.format("%.3f", a.getValue()));
                separator = ";";
            }
            System.out.println(sb.toString());
        }
    }
}