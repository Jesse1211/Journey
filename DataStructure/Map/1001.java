package DataStructure.Map;

import java.util.HashMap;

class Solution {

    int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, 1 }, { 1, 1 }, { 1, 0 },
            { 1, -1 }, { 0, -1 }, { 0, 0 } };

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[] ans = new int[queries.length];

        // store the row in which lamp is present
        HashMap<Integer, Integer> row = new HashMap<>();

        // store the column
        HashMap<Integer, Integer> col = new HashMap<>();

        // store the top-left diagonal
        HashMap<Integer, Integer> d1 = new HashMap<>();

        // store the top-right diagonal
        HashMap<Integer, Integer> d2 = new HashMap<>();

        // store grid lamps in form of hashing
        HashMap<Integer, Integer> idx = new HashMap<>();

        for (int i = 0; i < lamps.length; i++) {

            int x = lamps[i][0];
            int y = lamps[i][1];

            row.put(x, row.getOrDefault(x, 0) + 1);
            col.put(y, col.getOrDefault(y, 0) + 1);
            d1.put(x - y, d1.getOrDefault(x - y, 0) + 1);
            d2.put(x + y, d2.getOrDefault(x + y, 0) + 1);
            idx.put(x * n + y, idx.getOrDefault(x * n + y, 0) + 1);
        }

        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];

            if (row.containsKey(x) || col.containsKey(y) ||
                    d1.containsKey(x - y) ||
                    d2.containsKey(x + y)) {
                // 这个位置会被点亮
                ans[i] = 1;
            }

            // removal of lamps
            for (int j = 0; j < dir.length; j++) {
                int x1 = x + dir[j][0];
                int y1 = y + dir[j][1];

                if (x1 < n && x1 >= 0 && y1 < n &&
                        y1 >= 0 && idx.containsKey(x1 * n + y1) == true) {

                    int numOfLamps = idx.get(x1 * n + y1);

                    if (row.containsKey(x1))
                        row.put(x1, row.get(x1) - numOfLamps);
                    if (col.containsKey(y1))
                        col.put(y1, col.get(y1) - numOfLamps);
                    if (d1.containsKey(x1 - y1))
                        d1.put(x1 - y1, d1.get(x1 - y1) - numOfLamps);
                    if (d2.containsKey(x1 + y1))
                        d2.put(x1 + y1, d2.get(x1 + y1) - numOfLamps);

                    idx.remove(x1 * n + y1);
                    if (row.getOrDefault(x1, 0) == 0) {
                        row.remove(x1);
                    }
                    if (col.getOrDefault(y1, 0) == 0) {
                        col.remove(y1);
                    }
                    if (d1.getOrDefault(x1 - y1, 0) == 0) {
                        d1.remove(x1 - y1);
                    }
                    if (d2.getOrDefault(x1 + y1, 0) == 0) {
                        d2.remove(x1 + y1);
                    }

                }

            }
        }
        return ans;
    }
}