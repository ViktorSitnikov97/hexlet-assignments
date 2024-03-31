package exercise;

import java.util.Map;

// BEGIN
import java.util.LinkedHashMap;

public abstract class Tag {
    protected String nameTag;
    protected Map<String, String> attributes;

    public Tag(String nameTag, Map<String, String> attributes) {
        this.nameTag = nameTag;
        this.attributes = new LinkedHashMap<>(attributes);
        System.out.println(attributes);
    }

    public abstract String toString();


}

// END
