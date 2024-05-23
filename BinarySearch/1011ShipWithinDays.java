package BinarySearch;

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        int ans = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (check(weights, mid, days)) {
                ans = mid; 
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private boolean check(int[] weights, int capacity, int days) {
        int prev = 0;
        int day = 1;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > capacity) {return false;}
            if (prev + weights[i] > capacity) {
                prev = weights[i];
                day += 1;
            } else {
                prev += weights[i];
            }

            if (day > days) {
                return false;
            }
        
        }
        return true;

    }
}