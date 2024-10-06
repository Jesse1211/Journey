package Sub;

import java.util.Arrays;

/*
 * sub highlight: 这个难点在于，两个区间的长度是不一样的，所以要分两种情况讨论, 并且每个情况还有点复杂
 * 1. 不能用一个loop, 不确定谁先谁后
 * 2. 准备: 用一个sum数组来记录前i个数的和
 * 3. 两种情况:
 *      1: 先firstLen, 再secondLen
 *      2: 先secondLen, 再firstLen
 * /// 因为用的是Math.max, 不会存在两个值一定相连 / 重叠的情况
 */

class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] sums = new int[nums.length + 1];
        Arrays.fill(sums, 0);
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }

        int res = 0;
        int sum1 = 0;
        int sum2 = 0;

        // 先firstLen, 再secondLen
        for (int i = secondLen; i < sums.length - firstLen; i++) {
            sum2 = Math.max(sums[i] - sums[i - secondLen], sum2);
            sum1 = sums[i + firstLen] - sums[i];
            // sum1 = Math.max(sums[i + firstLen] - sums[i]); 这两个是误区, 每次loop要找的是left最大和 +
            // 当前right的和
            res = Math.max(sum1 + sum2, res);
        }

        // 先secondLen, 再firstLen
        sum1 = 0;
        for (int i = firstLen; i < sums.length - secondLen; i++) {
            sum1 = Math.max(sums[i] - sums[i - firstLen], sum1);
            sum2 = sums[i + secondLen] - sums[i];
            // sum2 = Math.max(sums[i + secondLen] - sums[i]); 这两个是误区, 每次loop要找的是left最大和 +
            // 当前right的和
            res = Math.max(sum1 + sum2, res);
        }
        return res;
    }
}