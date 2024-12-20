package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage{
    private String fName;
    public FileKV(String fileName, Map<String, String> dataMap) {
        Utils.writeFile(fileName, Utils.serialize(dataMap));
        this.fName = fileName;
    }

    @Override
    public void set(String key, String value) {
        Map<String , String> mData = Utils.deserialize(Utils.readFile(fName));
        mData.put(key, value);
        Utils.writeFile(fName, Utils.serialize(mData));
    }
    @Override
    public void unset(String key) {
        Map<String , String> mData = Utils.deserialize(Utils.readFile(fName));
        mData.remove(key);
        Utils.writeFile(fName, Utils.serialize(mData));
    }
    @Override
    public String get(String key, String defaultValue) {
        Map<String , String> mData = Utils.deserialize(Utils.readFile(fName));
        return mData.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String , String> mData = Utils.deserialize(Utils.readFile(fName));
        return new HashMap<>(mData);
    }
}

// END
