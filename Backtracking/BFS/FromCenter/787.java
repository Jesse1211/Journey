package Backtracking.BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 1. 判定bfs的逻辑:
*       从src到dst, 有多少条路径
 * 2. 用map&list&queue保存信息:
*       map: 保存每个城市能到达的地方: from: [to, cost]
*       list: 保存到每个城市的cost
*       queue: 当前level能走到的城市
 * 3. 用queue更新信息:
*       1. 从queue中取出当前城市
*       2. 从map中取出当前城市能到达的城市
        (如果已经有更小的cost, 则break)
*       3. 更新costs
*       4. 将当前城市能到达的城市加入queue
 */

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> srcToDst = new HashMap<>(); // from: [to, cost]
        for (int[] cur : flights) {
            int from = cur[0];
            int to = cur[1];
            int cost = cur[2];
            if (!srcToDst.containsKey(from)) {
                srcToDst.put(from, new ArrayList<>());
            }
            srcToDst.get(from).add(new int[] { to, cost });
        }

        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { src, 0 });

        while (!q.isEmpty() && k >= 0) {

            int level = q.size();
            for (int i = 0; i < level; i++) {

                int[] cur = q.poll();
                int curSrc = cur[0];
                int curCost = cur[1];

                var nexts = srcToDst.get(curSrc);
                if (nexts == null) {
                    continue;
                }

                for (var next : nexts) {
                    if (costs[next[0]] <= next[1] + curCost) {
                        continue;
                    }
                    costs[next[0]] = curCost + next[1];
                    q.offer(new int[] { next[0], next[1] + curCost });
                }

            }
            k--;
        }
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

}