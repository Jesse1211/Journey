package Design;

import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder {
    Queue<Integer> left;
    Queue<Integer> right;
    boolean isEven;

    public MedianFinder() {
        this.left = new PriorityQueue<>((a, b) -> b - a); // start from largest, can be 1 size bigger than right
        this.right = new PriorityQueue<>((a, b) -> a - b); // start from smallest
        this.isEven = true;
    }

    public void addNum(int num) {

        if (this.isEven) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }

        this.isEven = !this.isEven;
    }

    public double findMedian() {
        if (this.isEven) {
            return (this.left.peek() + this.right.peek()) / 2.0;
        } else {
            return this.left.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */