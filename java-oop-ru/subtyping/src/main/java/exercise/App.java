package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage inMap) {
        var iterMap = new HashMap<>(inMap.toMap());

        for (var itr : iterMap.entrySet()) {
            String gKey = itr.getKey();
            String gVal = itr.getValue();

            inMap.unset(gKey);
            inMap.set(gVal,gKey);
        }
    }
}

// END
