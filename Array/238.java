package Array;

import java.util.Arrays;

// highlight: 1. 两次遍历，第一次从左到右，第二次从右到左

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        Arrays.fill(ans, 1);
        int cur = 1;
        for (int i = 0; i < n; i++) {
            ans[i] *= cur;
            cur *= nums[i];
        }

        cur = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= cur;
            cur *= nums[i];
        }
        return ans;
    }
}