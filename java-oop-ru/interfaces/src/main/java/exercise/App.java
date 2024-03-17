package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, long n) {
        return apartments.stream()
                .sorted((Comparator.comparingDouble(Home::getArea)))
                .limit(n)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
