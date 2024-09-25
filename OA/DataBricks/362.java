package OA.DataBricks;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * Category: Design
 */
class HitCounter {
    Queue<Integer> q;

    public HitCounter() {
        this.q = new ArrayDeque<>();
    }

    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    public int getHits(int timestamp) {
        int oldest;
        while (!q.isEmpty()) {
            oldest = q.peek();
            if (timestamp - oldest < 300) {
                return q.size();
            } else {
                q.poll();
            }
        }
        return 0;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */