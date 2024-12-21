package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    SingleTag(String myTeg, Map<String, String> myMapa) {
        super(myTeg, myMapa);
    }

    public String toString() {
        StringBuilder retTeg = new StringBuilder("<");
        retTeg.append(myTeg);
        for (var iMap :myMapa.entrySet()) {
            retTeg.append(" ")
                    .append(iMap.getKey())
                    .append("=\"")
                    .append(iMap.getValue())
                    .append("\"");
        }

        retTeg.append(">");
        return retTeg.toString();
    }
}

// END
