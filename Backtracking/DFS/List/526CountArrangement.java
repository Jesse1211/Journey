package List;

class Solution {
    public int countArrangement(int n) {
        int[] nums = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nums[i] = i;
        }
        return dfs(nums, 1);
    }

    private int dfs(int[] nums, int index) {

        if (index == nums.length) {
            return 1;
        }

        int res = 0;
        for (int i = index; i < nums.length; i++) {
            // find bext candidate i for index,
            // put i into index position, then check
            swap(nums, i, index);
            if (nums[index] % index == 0 || index % nums[index] == 0) {
                res += dfs(nums, index + 1);
            }
            swap(nums, i, index);
        }
        return res;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}