package BFS.FromCenter;

import java.util.LinkedList;
import java.util.Queue;

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
        Queue<Integer> q = new LinkedList<>();

        if (n <= 0) {
            return 0;
        }

        q.offer(n);
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