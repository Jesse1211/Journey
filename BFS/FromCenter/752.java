package BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
 * 1. 判定bfs的逻辑:
*      从"0000"出发, 遍历所有的可能的密码
 * 2. 用Set&queue保存信息:
*      set: deadends 为了更快判断是否是deadends并且避免重复
*      queue: 保存将要访问的密码
 * 3. 用queue更新信息:
*      遍历当前密码的每一位, 生成新的密码
*      如果新的密码不是deadends, 并且没有访问过, 就加入queue
 */

class Solution {
    public int openLock(String[] deadends, String target) {
        int res = 0;
        if (target.equals("0000")) {
            return res;
        }

        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        if (set.contains("0000") || set.contains(target)) {
            return -1;
        }

        Queue<String> q = new ArrayDeque<>();
        q.offer("0000");
        set.add("0000");

        while (!q.isEmpty()) {
            int level = q.size();

            for (int i = 0; i < level; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return res;
                }

                for (int j = 0; j < 4; j++) {
                    char c = cur.charAt(j);
                    String up = cur.substring(0, j) + (c == '9' ? 0 : c - '0' + 1) + cur.substring(j + 1);
                    String dwn = cur.substring(0, j) + (c == '0' ? 9 : c - '0' - 1) + cur.substring(j + 1);

                    if (!set.contains(up)) {
                        set.add(up);
                        q.offer(up);
                    }

                    if (!set.contains(dwn)) {
                        set.add(dwn);
                        q.offer(dwn);
                    }
                }
            }
            res++;
        }
        return -1;
    }
}