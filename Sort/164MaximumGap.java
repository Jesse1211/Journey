import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        int res = 0;
        Arrays.sort(nums);

        if (n < 2) {return 0;}
        
        for (int i = 0; i < n-1; i++) {
            res = Math.max((nums[i+1] - nums[i]), res);
        }
        return res;
    }
}