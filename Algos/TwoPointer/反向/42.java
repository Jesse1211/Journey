package 反向;

class Solution {
    public int trap(int[] height) {
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {

            // update last visited max boundry
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                // water flows to lower bound
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }

        }
        return res;
    }
}