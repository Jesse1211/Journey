package Backtracking.BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 1. 判断bfs逻辑: 先从边界开始, 把符合条件的点加入queue, 然后逐层遍历
 * 2. 用queue, map, degrees保存信息:
 *      map: key是preq, value是cur (类似于children)
 *      queue: 保存将要访问的点
 *      degrees: 保存每个点的children数量来找出leaf
 * 3. 用queue更新信息:
 *      从leaf开始, 遍历所有相邻的点, 把符合条件的点加入queue并且改变degrees
 *      res取决于最后是不是所有的点都是leaf
 */

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> preqToCur = new HashMap<>();
        int[] degrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int preq = prerequisites[i][1];
            if (!preqToCur.containsKey(preq)) {
                preqToCur.put(preq, new ArrayList<>());
            }
            preqToCur.get(preq).add(cur);
            degrees[cur]++;
        }

        int leafNumber = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                q.offer(i);
                leafNumber++;
            }
        }

        while (!q.isEmpty()) {
            int preq = q.poll();
            if (preqToCur.get(preq) == null) {
                continue;
            }

            for (int cur : preqToCur.get(preq)) {
                degrees[cur]--;
                if (degrees[cur] == 0) {
                    leafNumber++;
                    q.offer(cur);
                }
            }
        }

        return leafNumber == numCourses;
    }
}