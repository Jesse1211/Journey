package SlidingWindow;

class Solution {
    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int left = 0;
        int right = 0;
        int maxFreq = 0;
        int[] freq = new int[2];

        while (right < nums.length) {
            int cur = nums[right];
            freq[cur]++;

            if (cur == 1) {
                maxFreq = Math.max(freq[cur], maxFreq);
            }

            while (right - left + 1 > maxFreq + k) {
                int curLeft = nums[left];
                freq[curLeft]--;
                left++;
            }

            res = Math.max(right - left + 1, res);
            right++;
        }
        return res;
    }
}