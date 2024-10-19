package Design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {
    Map<String, TreeMap<Integer, String>> store;

    // key: {time: value}
    public TimeMap() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, x -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        Integer version = (store.containsKey(key)) ? store.get(key).floorKey(timestamp) : null;
        return (version != null) ? store.get(key).get(version) : "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */