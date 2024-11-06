
/*
 * highlight: 这啥玩意
 */
class Solution {

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}

class Solution2 {
    public int findDuplicate(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int count = 0;
            int mid = left + (right - left) / 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) { // 查找所有不大于mid的值 - 如果左边没问题, 那找到的数量应该小于 & 等于mid
                    count++;
                }
            }

            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}