package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    private final Map<String, String> intData;

    public InMemoryKV(Map<String, String> getedMap){
        this.intData = new HashMap<>(getedMap);
    }

    @Override
    public void set(String key, String value) {
        intData.put(key, value);
    }
    @Override
    public void unset(String key) {
        intData.remove(key);
    }
    @Override
    public String get(String key, String defaultValue) {
        return intData.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(intData);
    }
}

// END
