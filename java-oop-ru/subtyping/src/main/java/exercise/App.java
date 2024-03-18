package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class App {
    public static Map<String, String> swapKeyValue(KeyValueStorage storage) {
        Map<String, String> currentMap = new HashMap<>(storage.toMap());

        currentMap.forEach((k, v) -> {
            storage.unset(k);
            storage.set(v, k);
        });

        return storage.toMap();
    }
}
// END
