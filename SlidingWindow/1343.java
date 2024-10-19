package SlidingWindow;

class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int res = 0;
        int sum = 0;
        int left = 0;
        int right = 0;

        while (right < arr.length) {
            sum += arr[right];

            if (right - left + 1 < k) {
                right++;
                continue;
            }

            if (sum / k >= threshold) {
                res++;
            }
            sum -= arr[left];
            left++;
            right++;
        }
        return res;
    }
}