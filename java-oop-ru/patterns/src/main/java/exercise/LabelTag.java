package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String type;
    private final TagInterface tagInterface;

    public LabelTag(String type, TagInterface tagInterface) {
        this.type = type;
        this.tagInterface = tagInterface;
    }

    public String render() {
        return "<label>" + type + tagInterface.render() + "</label>";
    }
}
// END

