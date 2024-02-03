package exercise;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {
    public static String getForwardedVariables(String stringFromFile) {

        if (stringFromFile.isEmpty()) {
            return "";
        }

        String[] environmentVariables = stringFromFile.split("environment=\"|\n|\"|\\,");

        Predicate<String> withSymbol = s -> s.endsWith("~");
        Predicate<String> withoutSymbol = withSymbol.negate();

        return Arrays.stream(environmentVariables)
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(s -> s.substring("X_FORWARDED_".length()))
                .filter(withoutSymbol)
                .collect(Collectors.joining(","));
    }
}
//END
