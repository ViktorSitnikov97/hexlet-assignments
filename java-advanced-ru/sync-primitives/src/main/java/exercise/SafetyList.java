package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private final List<Integer> innerList;

    public SafetyList() {
        innerList = new ArrayList<>();
    }

    public synchronized void add(int num) {
        innerList.add(num);
    }

    public int get(int index) {
        return innerList.get(index);
    }

    public int getSize() {
        return innerList.size();
    }
    // END
}
