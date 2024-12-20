package exercise;

// BEGIN
class LabelTag implements TagInterface {
    private final String inputData;
    TagInterface inputTag;
    public LabelTag (String inputData, TagInterface inputTag) {
        this.inputData = inputData;
        this.inputTag = inputTag;
    }

    @Override
    public String render() {
        return "<label>" + inputData + inputTag.render() + "</label>";
    }
}

// END
