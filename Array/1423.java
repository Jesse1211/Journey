
/* highlight: 用prefix sum做还挺难的, 但是用sliding window就很简单
 * 1. 确定可以用一个forloop就可以完成
 * 2. 用一个sum来记录小sum的subarrays with length n - k
 * 3. 判定逻辑: 
 *      制作window: 从0开始，到n - k结束
 *      通过winsow的sum来找到最小的subarray
 *      通过sum - minSubarray来找到答案
 */

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = 0;
        int minLen = n - k;
        int minLenCur = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += cardPoints[i];
            if (i < minLen) {
                minLenCur += cardPoints[i];
                continue;
            }
            res = Math.min(res, minLenCur);
            minLenCur -= cardPoints[i - minLen];
            minLenCur += cardPoints[i];
        }

        res = Math.min(res, minLenCur);
        return sum - res;
    }
}