package Sub;

/*
 * sub highlight: 有点抽象
 */

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

// class Solution {
//     public int maxProduct(int[] nums) {
//         int min = nums[0]; // 负数
//         int max = nums[0]; // 正数
//         int res = max;

//         for (int i = 1; i < nums.length; i++) {
//             if (min == 0) { // 遇到0, reset
//                 min = nums[i];
//                 max = nums[i];
//             } else {
//                 int tempMin = min;
//                 min = Math.min(min * nums[i], max * nums[i]);
//                 max = Math.max(tempMin * nums[i], max * nums[i]);

//                 // 如果更新后的值不如当前的值, 那就reset
//                 if (max < nums[i]) {
//                     max = nums[i];
//                 }

//                 if (min > nums[i]) {
//                     min = nums[i];
//                 }
//             }
//             res = Math.max(min, res);
//             res = Math.max(res, max);
//         }
//         return res;
//     }
// }