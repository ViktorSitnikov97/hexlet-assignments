package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private final Map<String, String> map1;
    private final Map<String, String> map2;

    public InMemoryKV(Map<String, String> map) {
        this.map1 = new HashMap<>(map);
        this.map2 = map;
    }

    @Override
    public void set(String key, String value) {
        map1.put(key, value);
    }
    @Override
    public void unset(String key) {
        map1.remove(key);
    }
    @Override
    public String get(String key, String defaultValue) {
        return map1.getOrDefault(key, defaultValue);
    }
    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(map1);
    }
}
// END
