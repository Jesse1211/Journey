package DataStructure.Map;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String customSortString(String order, String s) {
        Map<Character, Integer> freqS = new HashMap<>(); // char : freq

        for (char c : s.toCharArray()) {
            freqS.put(c, freqS.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (char c : order.toCharArray()) {
            while (freqS.containsKey(c) && freqS.get(c) > 0) {
                freqS.put(c, freqS.get(c) - 1);
                sb.append(c);
            }
        }

        for (var entry : freqS.entrySet()) {
            char c = entry.getKey();
            while (entry.getValue() > 0) {
                freqS.put(c, freqS.get(c) - 1);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}