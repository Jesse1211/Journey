package BFS.ToCenter;

import java.util.*;

/* highlight: 高频题 - 从degree为1的点开始bfs, 最后剩下的点就是root
 * 1. 判断bfs逻辑: 先从边界开始, 把符合条件的点加入queue, 然后逐层遍历
 * 2. 用queue & map保存信息:
 *      用map保存每个点的相邻点并且记录degree
 *      queue保存将要访问的点也就是degree为1的点 (从边界开始)
 * 3. 用queue更新信息:
 *      遍历所有相邻的点, 把符合条件的点加入queue并且改变degree
 */

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] degrees = new int[n];

        for (int[] edge : edges) {
            int point1 = edge[0];
            int point2 = edge[1];
            if (!map.containsKey(point1)) {
                map.put(point1, new ArrayList<>());
            }

            if (!map.containsKey(point2)) {
                map.put(point2, new ArrayList<>());
            }

            map.get(point1).add(point2);
            map.get(point2).add(point1);
            degrees[point1]++;
            degrees[point2]++;
        }

        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                leaves.offer(i);
            }
        }

        while (n > 2) { // max is 2
            int size = leaves.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                for (int neighbor : map.get(leaf)) {
                    degrees[neighbor]--;
                    if (degrees[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }

        res.addAll(leaves);
        return res;
    }
}
