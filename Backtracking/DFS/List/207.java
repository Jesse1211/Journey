package List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* 
 * 1. 判定dfs的parameters: id, map, visited
 *      如果map没有id, 那就说明没有需要上的课了
 *      如果visited中有id, 直接返回false
 *      通过id找到preq, 进一步遍历preq
 * 2. dfs 何时停止: 
 *      如果没有preq, 直接返回true
 *      如果visited中有id, 直接返回false
 * 3. dfs 如何更新: 
 *      添加id到visited
 *      遍历他的preq, 需要每一个preq都return true
 *      如果当前课程可以上, 那就从map中移除
 */

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
        // does not have preq
        if (!map.containsKey(id)) {
            return true;
        }

        // visited
        if (visited.contains(id)) {
            return false;
        }

        visited.add(id);
        for (var preq : map.get(id)) {
            if (!dfs(preq, map, visited)) {
                return false;
            }
        }
        visited.remove(id);
        map.remove(id);
        return true;
    }
}