
class Solution {
    public int maxProduct(int[] nums) {
        int maxPos = nums[0];
        int maxNeg = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxPos;
                maxPos = maxNeg;
                maxNeg = temp;
            }

            maxPos = Math.max(nums[i], maxPos * nums[i]);
            maxNeg = Math.min(nums[i], maxNeg * nums[i]);

            if (maxPos > res) {
                res = maxPos;
            }
        }

        return res;
    }
}