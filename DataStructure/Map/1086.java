import java.util.*;

class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            if (!map.containsKey(items[i][0])) {
                map.put(items[i][0], new ArrayList());
            }
            map.get(items[i][0]).add(items[i][1]);
        }

        int k = 0;
        int[][] res = new int[map.size()][2];
        for (int i : map.keySet()) {
            int average = 0;
            int d = map.get(i).size() < 5 ? map.get(i).size() : 5;
            for (int j = 0; j < 5; j++) {
                Collections.sort(map.get(i), Collections.reverseOrder());
                average += map.get(i).get(j);
            }
            res[k][0] = i;
            res[k][1] = average / d;
            k++;
        }
        return res;
    }
}