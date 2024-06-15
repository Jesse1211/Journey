import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        // {cur: preq}
        for (int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int preq = prerequisites[i][1];
            if (!map.containsKey(cur)) {
                map.put(cur, new ArrayList<>());
            }
            map.get(cur).add(preq);
        }

        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, map, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int id, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        if (!map.containsKey(id)) {
            return true;
        } // does not have preq
        if (visited.contains(id)) {
            return false;
        } // visited
        visited.add(id);
        for (var preq : map.get(id)) {
            if (!dfs(preq, map, visited)) {
                return false;
            }
        }
        visited.remove(id);
        map.put(id, new ArrayList<>());
        return true;
    }
}