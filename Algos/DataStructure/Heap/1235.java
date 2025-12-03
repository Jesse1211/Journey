package DataStructure.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    class Job {
        int start;
        int end;
        int profit;

        Job(int end, int profit) {
            this.end = end;
            this.profit = profit;
        }

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            jobs.add(
                    new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(jobs, (a, b) -> a.start - b.start);

        return helper(jobs);
    }

    private int helper(List<Job> jobs) {
        int res = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.end - b.end);

        for (Job job : jobs) {
            while (!pq.isEmpty() && pq.peek().end <= job.start) {
                res = Math.max(res, pq.poll().profit);
            }
            pq.offer(new Job(job.end, job.profit + res));
        }

        while (!pq.isEmpty()) {
            res = Math.max(res, pq.poll().profit);
        }

        return res;
    }
}