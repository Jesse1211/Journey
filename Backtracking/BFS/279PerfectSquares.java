package Backtracking.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int numSquares(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        if (n <= 0) {
            return 0;
        }

        q.offer(n);
        set.add(n);
        int res = 0;
        while (!q.isEmpty()) {
            res++;

            int level = q.size();
            for (int i = 0; i < level; i++) {

                int cur = q.poll();
                for (int j = 1; j < cur; j++) {
                    if (j * j == cur) {
                        return res;
                    } else if (j * j > cur) {
                        break;
                    } else {
                        q.offer(cur - j * j);
                    }
                }

            }

        }
        return res;
    }
}