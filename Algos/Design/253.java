package Design;

import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int minMeetingRooms(int[][] intervals) {

        PriorityQueue<Integer> q = new PriorityQueue<Integer>(intervals.length);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        q.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < q.peek()) {
                q.offer(intervals[i][1]);
            } else {
                q.poll();
                q.offer(intervals[i][1]);
            }
        }
        return q.size();
    }
}