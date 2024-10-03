package BinarySearchInclusive;

import java.util.Random;

// meta
class Solution {

    private int[] nums;
    private int sum;
    private Random rand;

    public Solution(int[] w) {
        this.nums = new int[w.length];
        this.rand = new Random();

        this.sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            this.nums[i] = sum;
        }
    }

    public int pickIndex() {
        if (this.sum == 0)
            return -1;

        int n = this.rand.nextInt(1, this.sum + 1);
        return binarySearch(n);
    }

    private int binarySearch(int n) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (this.nums[mid] == n) {
                return mid;
            }
            if (this.nums[mid] > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */