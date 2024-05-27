package OA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.fastprep.io/problems/snowflake-radio-waves
class Solution {

    public int radioWaves(int network_nodes, int[] network_from, int[] network_to, int[] frequency) {
        // frequency only 1,2,3 units
        // network_nodes = num of devices
        // network_from, network_to: connected devices
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < network_from.length; i++) {
            int from = network_from[i];
            int to = network_to[i];
            if (!map.containsKey(from)) {
                map.put(from, new HashSet<>());
            }
            if (!map.containsKey(to)) {
                map.put(to, new HashSet<>());
            }
            map.get(from).add(to);
            map.get(to).add(from);
        }
        // map: {from: [to1, to2, ...], to: [from1, from2, ...]}
        int res = 0;
        Set<Integer> visited = new HashSet<Integer>();
        for (var ent : map.entrySet()) {
            int from = ent.getKey();
            Set<Integer> tos = ent.getValue();
            res = Math.max(dfs(map, from, frequency, tos, 0, visited), res);
        }
        return res;
    }

    private int dfs(Map<Integer, Set<Integer>> map, int from, int[] frequency, Set<Integer> tos, int res,
            Set<Integer> visited) {
        int curFreq = frequency[from - 1];
        for (int to : tos) {
            if (frequency[to - 1] <= curFreq + 1 && frequency[to - 1] >= curFreq - 1 && !visited.contains(to)) {
                visited.add(to);
                res = dfs(map, to, frequency, tos, res, visited) + 1;
                visited.remove(to);
            }
        }
        return res;
    }
}