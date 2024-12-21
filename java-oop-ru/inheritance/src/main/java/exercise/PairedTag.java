package exercise;

import java.util.Map;
import java.util.List;
// import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {
    String intText;
    List<Tag> children;

    PairedTag(String myTeg, Map<String, String> myMapa, String intText, List<Tag> children) {
        super(myTeg, myMapa);
        this.intText = intText;
        this.children = children;
    }

    public String toString(){
        StringBuilder retTeg;
        retTeg = new StringBuilder("<");
        retTeg.append(myTeg);
        for (var iMap : myMapa.entrySet()) {
            retTeg.append(" ").append(iMap.getKey())
                    .append("=\"").append(iMap.getValue()).append("\"");
        }

        retTeg.append(">").append(intText);

        for (var iList : children) {
            retTeg.append(iList.toString());
        }
        retTeg.append("</").append(myTeg).append(">");

        return retTeg.toString();
    }
}

// END
