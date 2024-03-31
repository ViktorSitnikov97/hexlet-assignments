package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
import java.util.ArrayList;

public final class PairedTag extends Tag {

    private final String bodyTag;
    private final List<Tag> children;

    public PairedTag(String nameTag, Map<String, String> attributes, String bodyTag, List<Tag> children) {
        super(nameTag, attributes);
        this.bodyTag = bodyTag;
        this.children = new ArrayList<>(children);
    }

    private String handler(Map<String, String> attributes) {
        StringBuilder sb = new StringBuilder("");
        if (!attributes.isEmpty()) {
            attributes.forEach((key, value) -> sb.append(" " + key + "=" + "\"" + value + "\""));
        }
        return sb.toString();
    }

    private String deepHandler(List<Tag> childrenTag) {

        StringBuilder sb = new StringBuilder("");

        for (Tag singleTag : childrenTag) {
            sb.append(String.format("<" + singleTag.nameTag + "%s", handler(singleTag.attributes) + ">"));
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return String.format("<" + nameTag
                + "%s", handler(attributes)
                + ">"
                + bodyTag
                + deepHandler(children)
                + "</"
                + nameTag
                + ">");
    }

}
// END
