package BFS.ToCenter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean canMeasureWater(int x, int y, int target) {
        if (x + y < target) {
            return false;
        }

        if (x + y == target || x == target || y == target) {
            return true;
        }

        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited.add(0);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == target) {
                return true;
            }

            // add x
            if (cur + x <= x + y && !visited.contains(cur + x)) {
                q.offer(cur + x);
                visited.add(cur + x);
            }

            // dump x
            if (cur - x >= 0 && !visited.contains(cur - x)) {
                q.offer(cur - x);
                visited.add(cur - x);
            }

            // add y
            if (cur + y <= x + y && !visited.contains(cur + y)) {
                q.offer(cur + y);
                visited.add(cur + y);
            }
            // dump y
            if (cur - y >= 0 && !visited.contains(cur - y)) {
                q.offer(cur - y);
                visited.add(cur - y);
            }
        }

        return false;
    }
}