package BFS.ToCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 和207的区别在于:
 * 210需要返回一个list, 207只需要返回boolean
 * 但是逻辑是一样的, 207中每次找到一个leaf就+1, 210中每次找到一个leaf就把它加入list
 */

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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

        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                q.offer(i);
                ans.add(i);
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
                    ans.add(cur);
                    q.offer(cur);
                }
            }
        }

        if (ans.size() != numCourses) {
            return new int[0];
        }

        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}