package others;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class NGrams {

    private static Map<String, Integer> buildWordMap(List<String> lines) {
        Map<String, Integer> wordCount = new HashMap<>();
        lines.stream().map(l -> l.split("\\s")).map(a -> Arrays.asList(a)).
                forEach(s -> s.stream().forEach(z -> wordCount.merge(z, 1, Integer::sum)));
        return wordCount;
    }

    private static Map<String, Integer> nextWordFrequency(String input, List<String> lines) {
        Map<String, Integer> nextWords = new LinkedHashMap<>();
        for (String line : lines) {
            String[] words = line.split("\\s");
            for (int i = 0; i < words.length - 1; i++) {
                if (words[i].equals(input)) {
                    nextWords.merge(words[i + 1], 1, Integer::sum);
                }
            }
        }
        return nextWords;
    }

    private static Map<String, Double> cdf(Map<String, Integer> nextWords) {
        Map<String, Double> cdf = new LinkedHashMap<>();
        BigDecimal probSum = BigDecimal.ZERO;
        int sumVals = nextWords.values().stream().reduce(Integer::sum).orElse(0);
        for (Map.Entry<String, Integer> a : nextWords.entrySet()) {
            BigDecimal pmf = BigDecimal.valueOf(a.getValue()).divide(BigDecimal.valueOf(sumVals), MathContext.DECIMAL32);
            probSum = probSum.add(pmf);
            cdf.put(a.getKey(), probSum.setScale(3, RoundingMode.CEILING).doubleValue());
        }
        return cdf;
    }

    public static void predict(String sent, int l, int n) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/ngrams.txt"));
            String tempOut = "";
            String out = sent + " ";
            for (int i = 0; i < (n - l); i++) {
                //Map<String, Integer> wordMap = buildWordMap(lines);
                Map<String, Integer> nextWords = nextWordFrequency(sent, lines);
                Map<String, Double> cdfs = cdf(nextWords);
                Random r = new Random();
                double randomValue = 0 + (1 - 0) * r.nextDouble();
                String abc = "";
                if (!cdfs.isEmpty()) {
                    for (Map.Entry<String, Double> cdf : cdfs.entrySet()) {
                        if (randomValue <= cdf.getValue()) {
                            abc = cdf.getKey();
                            break;
                        }
                    }
                }
                tempOut = abc;
                out = out + tempOut + " ";
                sent = tempOut;
                System.out.println(randomValue);
                System.out.println(nextWords);
                System.out.println(cdfs);
                System.out.println(sent);
            }
            System.out.println(out);
            System.out.println(tempOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // How to take input either direct from inputStream
        // Or file based input
        System.out.println(
                Double.valueOf(String.format("%.3f", 7.5)));
        //predict("is", "is".length(), 10);

    }
}
