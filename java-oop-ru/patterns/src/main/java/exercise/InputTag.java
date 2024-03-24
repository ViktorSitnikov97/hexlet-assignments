package exercise;

// BEGIN
public class InputTag implements TagInterface {
    private final String type;
    private final String value;

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String render() {
        return "<input type=\"" + type + "\" value=\"" + value + "\">";
    }

}

// END

