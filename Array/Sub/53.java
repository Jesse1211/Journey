package Sub;

/*
 * sub highlight: 和prefix sum使用
 * 1. 确定一个forloop就可以完成
 * 2. 用一个level来记录当前的和，如果level小于0，就把level置为当前的值
 * 3. res是所有level中最大的值
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int level = nums[0];
        for (int i = 1; i < nums.length; i++) {
            level += nums[i];
            if (level < nums[i]) {
                level = nums[i];
            }
            res = Math.max(res, level);
        }
        return res;
    }
}