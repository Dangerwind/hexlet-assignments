package exercise;

import java.util.ArrayList;

class SafetyList {
    // BEGIN

    ArrayList<Integer> list; // = new ArrayList<>();

    public SafetyList() {
        list = new ArrayList<>();
    }

    public synchronized void add(int value) {
        list.add(value);
    }

    public int getSize() {
        return list.size();
    }

    public int get(int index) {
        return list.get(index);
    }

    
    // END
}
