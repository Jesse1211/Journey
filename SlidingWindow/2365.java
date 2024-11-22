package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        long res = 0;
        Map<Integer, Long> map = new HashMap<>(); // task : last complete day

        for (int i = 0; i < tasks.length; i++) {
            res++;
            int task = tasks[i];
            if (map.containsKey(task)) {
                long prevDay = map.get(task);
                if (res - prevDay <= space) {
                    res += space - (res - prevDay) + 1;
                }
            }
            map.put(task, res);
        }
        return res;
    }
}