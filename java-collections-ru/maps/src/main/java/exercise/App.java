package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {

        Map<String, Integer> wordsCount = new HashMap<>();
        if (sentence.isEmpty()) {
            return wordsCount;
        }
        String[] words = sentence.split(" ");

        for (var word : words) {
            var count = wordsCount.getOrDefault(word, 0);
            wordsCount.put(word, count + 1);
        }
        return wordsCount;
    }

    public static String toString(Map<String, Integer> wordsCount) {
        if (wordsCount.isEmpty()) {
            return "{}";
        }
        StringBuilder result = new StringBuilder();
        result.append("{" + "\n");
        wordsCount.forEach((key, value) -> {
            result.append("  " + key + ": " + value + "\n");
        });
        result.append("}");
        return result.toString();
    }

    public static void main(String[] args) {
        String sentence = "java is the best programming language java";
        Map wordsCount = App.getWordCount(sentence);
        System.out.println(wordsCount);
        String result = App.toString(wordsCount);
        System.out.println(result);

    }
}
// END
