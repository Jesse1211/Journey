package Array;

class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] maxFromLeft = new int[n];
        int[] maxFromRight = new int[n];

        maxFromLeft[0] = nums[0];
        maxFromRight[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            maxFromLeft[i] = Math.max(maxFromLeft[i - 1], nums[i]);
            maxFromRight[n - i - 1] = Math.max(maxFromRight[n - i], nums[n - i - 1]);
        }

        long res = 0;
        for (int i = 1; i < n - 1; i++) {
            res = Math.max(res, (long) (maxFromLeft[i - 1] - nums[i]) * (long) maxFromRight[i + 1]);
        }
        return res;
    }
}