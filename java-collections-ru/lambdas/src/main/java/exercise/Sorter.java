package exercise;

//import java.util.Comparator;
import java.util.Map;
import java.util.List;
//import java.time.LocalDate;
//import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted((user1, user2) ->
                        String.CASE_INSENSITIVE_ORDER.compare(user1.get("birthday"), user2.get("birthday")))
                .map(user -> user.get("name"))
                .toList();
    }
}
// END
