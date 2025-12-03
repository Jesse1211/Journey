package BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
 * 1. 判定bfs的逻辑:
*      从结果出发, 逐层删减, 最快能找到 x * x = cur结束
 * 2. 用queue保存每层cur的信息:
*      保存当前剩余的值
 * 3. 用queue更新信息:
*      每次拿出来的cur都要从0到cur来找结果 (log n)
*      如果cur == x * x, 则返回结果
*      如果cur < x * x, 则break
*      如果cur > x * x, 则更新cur = cur - x * x
 */
class Solution {
    public int numSquares(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        int res = 0;

        while (!q.isEmpty()) {
            int level = q.size();
            res++;

            while (level-- > 0) {
                int cur = q.poll();
                for (int i = 0; i <= cur; i++) {
                    int left = cur - i * i;

                    if (left == 0) {
                        return res;
                    } else if (left < 0) {
                        break;
                    } else if (visited.contains(left)) {
                        continue;
                    }

                    q.offer(cur - i * i);
                    visited.add(left);
                }
            }
        }

        return res;
    }
}