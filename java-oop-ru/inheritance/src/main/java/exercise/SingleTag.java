package exercise;

import java.util.Map;

// BEGIN

public final class SingleTag extends Tag {
    public SingleTag(String nameTag, Map<String, String> attributes) {
        super(nameTag, attributes);
    }
    private String handler(Map<String, String> attributes) {
        StringBuilder sb = new StringBuilder("");

        if (!attributes.isEmpty()) {
            attributes.forEach((key, value) -> sb.append(" " + key + "=" + "\"" + value + "\""));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("<" + nameTag + "%s", handler(attributes) + ">");
    }
}
// END
