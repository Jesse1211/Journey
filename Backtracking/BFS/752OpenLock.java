package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }

        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        if (deadendSet.contains("0000")) {
            return -1;
        }

        Queue<String> q = new ArrayDeque<>();
        q.offer("0000");

        int res = 0;
        deadendSet.add("0000");

        while (!q.isEmpty()) {

            int level = q.size();
            for (int k = 0; k < level; k++) {
                String cur = q.poll();
                if (cur == target) {
                    return res;
                }

                for (int i = 0; i < 4; i++) {
                    char c = cur.charAt(i);

                    String up = cur.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + cur.substring(i + 1);
                    String dwn = cur.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + cur.substring(i + 1);

                    if (up.equals(target) || dwn.equals(target))
                        return res + 1;

                    if (!deadendSet.contains(up)) {
                        q.offer(up);
                        deadendSet.add(up);
                    }
                    if (!deadendSet.contains(dwn)) {
                        q.offer(dwn);
                        deadendSet.add(dwn);
                    }
                }
            }
            res++;
        }
        return -1;
    }
}