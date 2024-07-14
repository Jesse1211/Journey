package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] preq = new ArrayList[numCourses];
        int[] degrees = new int[numCourses];

        for (int[] i : prerequisites) {
            int sec = i[0];
            int first = i[1];
            if (preq[first] == null) {
                preq[first] = new ArrayList<>();
            }
            preq[first].add(sec);
            degrees[sec]++;
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (degrees[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {

            int cur = q.poll();
            ans.add(cur);

            if (preq[cur] == null) {
                continue;
            }

            for (var sec : preq[cur]) {
                degrees[sec]--;
                if (degrees[sec] == 0) {
                    q.offer(sec);
                }
            }

        }

        return ans.size() == numCourses;
    }
}