package SlidingWindow;

class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int sum = 0; // total unsatisfied
        int res = 0;
        int right = 0;
        int left = 0;

        while (right < customers.length) {
            sum += customers[right] * grumpy[right];

            if (right - left + 1 > minutes) {
                sum -= customers[left] * grumpy[left];
                left++;
            }

            res = Math.max(res, sum);
            right++;
        }

        // res = max unsatisfied in minutes window + all satisfied
        for (int i = 0; i < customers.length; i++) {
            res += customers[i] * (1 - grumpy[i]);
        }

        return res;
    }
}