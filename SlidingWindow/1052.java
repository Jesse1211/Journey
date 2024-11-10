package SlidingWindow;

class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int max = 0, n = customers.length;
        max = maxWhenGrumpy(customers, grumpy, minutes);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0)
                sum += customers[i];
        }
        return max + sum;
    }

    private int maxWhenGrumpy(int[] customers, int[] g, int minutes) {
        int res = 0, fast = 0, slow = 0, sum = 0, n = customers.length;

        while (fast < minutes) {
            if (g[fast] == 1)
                sum += customers[fast];
            fast++;
        }
        res = Math.max(res, sum);

        while (fast < n) {
            if (g[slow] == 1)
                sum -= customers[slow];
            if (g[fast] == 1)
                sum += customers[fast];
            res = Math.max(res, sum);
            fast++;
            slow++;
        }
        return res;

    }
}