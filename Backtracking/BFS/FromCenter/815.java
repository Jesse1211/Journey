package Backtracking.BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
* 1. 判定bfs的逻辑:
*      从source出发, 遍历所有能去的车站
 * 2. 用Set&queue保存信息:
*      set: 避免重复
*      queue: 保存将要访问的车站
 * 3. 用queue更新信息:
*      遍历从当前车站能去的所有车站, 并且没有访问过, 就加入queue
 */

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> stopToBus = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            int[] stops = routes[i];
            for (int stop : stops) {
                if (!stopToBus.containsKey(stop)) {
                    stopToBus.put(stop, new ArrayList<>());
                }
                stopToBus.get(stop).add(i);
            }
        }

        Set<Integer> visitedBus = new HashSet<>();
        Set<Integer> visitedStop = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(source);

        int res = 0;

        while (!q.isEmpty()) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                int curStop = q.poll();
                if (curStop == target) {
                    return res;
                }

                var buses = stopToBus.get(curStop);
                if (buses == null) {
                    continue;
                }

                for (int bus : buses) {
                    if (visitedBus.contains(bus)) {
                        continue;
                    }
                    visitedBus.add(bus);
                    for (int stop : routes[bus]) {
                        if (visitedStop.contains(stop)) {
                            continue;
                        }
                        visitedStop.add(stop);
                        q.add(stop);
                    }
                }
            }
            res++;
        }
        return -1;
    }
}