package Basic;

/*
 * highlight: 这个难点在于，两个区间的长度是不一样的，所以要分两种情况讨论, 并且每个情况还有点复杂
 * 1. 不能用一个loop, 不确定谁先谁后
 * 2. 准备: 用一个sum数组来记录前i个数的和
 * 3. 两种情况:
 *      1: 先firstLen, 再secondLen
 *      2: 先secondLen, 再firstLen
 * /// 因为用的是Math.max, 不会存在两个值一定相连 / 重叠的情况
 */

class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] sum = new int[nums.length + 1];

        for (int i = 1; i < nums.length + 1; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int res = 0;
        int max = 0;

        // 1: firstLen is before secondLen
        for (int i = firstLen; i <= nums.length - secondLen; i++) {
            int before = sum[i] - sum[i - firstLen];
            int after = sum[i + secondLen] - sum[i];
            max = Math.max(max, before);
            res = Math.max(res, max + after);
        }

        // 2: vise-versa
        max = 0;
        for (int i = secondLen; i <= nums.length - firstLen; i++) {
            int before = sum[i] - sum[i - secondLen];
            int after = sum[i + firstLen] - sum[i];
            max = Math.max(max, before);
            res = Math.max(res, max + after);
        }
        return res;
    }
}