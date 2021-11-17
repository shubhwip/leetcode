package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// Better Version of Ngrams
// Handling of Corner Cases
public class ModifiedNgrams {
    private final static String corpus = "Mary had a little lamb its fleece was white as snow;\n" +
            "And everywhere that Mary went, the lamb was sure to go.\n" +
            "It followed her to school one day, which was against the rule;\n" +
            "It made the children laugh and play, to see a lamb at school.\n" +
            "And so the teacher turned it out, but still it lingered near,\n" +
            "And waited patiently about till Mary did appear.\n" +
            "\"Why does the lamb love Mary so?\" the eager children cry;\"Why, Mary loves the lamb, you know\" the teacher did reply.\"";

    // 2,Mary
    //did,0.200;had,0.200;loves,0.200;so?",0.200;went,,0.200
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String textLine;
        while ((textLine = bufferedReader.readLine()) != null) {
            String[] input = textLine.split(",");
            if (input.length != 2) {
                System.out.println("Please specify two arguments to this programs correctly");
                System.exit(1);
            }
            String searchText = input[1].trim();
            int ngramLength = Integer.parseInt(input[0]);
            // Building Next Set of words
            Map<String, Integer> words = findNextWords(ngramLength, searchText);
            // Calculate Probablity
            Map<String, BigDecimal> probablity = calculateProbablity(words);
            // Display answers
            display(probablity);
        }
    }

    private static void display(Map<String, BigDecimal> probablity) {
        Comparator<Map.Entry<String, BigDecimal>> comp = Map.Entry.
                <String, BigDecimal>comparingByValue()
                .reversed()
                .thenComparing(Map.Entry.comparingByKey());

        Map<String, BigDecimal> newMap = probablity.entrySet().stream().sorted(comp).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        Map<String, BigDecimal> lhmap =
                probablity.entrySet().stream().sorted(comp)
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,      // merge function
                                LinkedHashMap::new));
        StringBuilder sb = new StringBuilder();
        String separator = "";
        for (Map.Entry<String, BigDecimal> result : lhmap.entrySet()) {
            sb.append(separator).append(result.getKey() + "," + result.getValue().setScale(3));
            separator = ";";
        }
        System.out.println(sb);
    }

    private static Map<String, BigDecimal> calculateProbablity(Map<String, Integer> words) {
        Map<String, BigDecimal> cdf = new LinkedHashMap<>();
        int sumVals = words.values().stream().reduce(Integer::sum).orElse(0);
        for (Map.Entry<String, Integer> a : words.entrySet()) {
            BigDecimal probablity = BigDecimal.valueOf(a.getValue()).divide(BigDecimal.valueOf(sumVals), MathContext.DECIMAL32).setScale(3);
            cdf.put(a.getKey(), probablity);
        }
        return cdf;

    }

    private static Map<String, Integer> findNextWords(int ngramLength, String searchText) {
        Map<String, Integer> nextWordsMap = new HashMap<>();
        String[] words = corpus.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("^[^a-zA-Z0-9\\s]+|[^a-zA-Z0-9\\s]+$", "");
        }
        for (int i = 0; i < words.length - (ngramLength - 1); i++) {
            if (words[i].equals(searchText)) {
                String separator = "";
                StringBuilder sb = new StringBuilder();
                for (int j = 1; j < ngramLength; j++) {
                    sb.append(separator + words[i + j]);
                    separator = " ";
                }
                nextWordsMap.merge(sb.toString(), 1, Integer::sum);
            }
        }
        return nextWordsMap;
    }
}
