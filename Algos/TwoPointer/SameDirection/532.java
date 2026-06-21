package SameDirection;

import java.util.HashMap;

// highlight: 可以sort也可以用hashmap解决
import java.util.Arrays;

class Solution1 {
    public int findPairs(int[] nums, int k) {

        Arrays.sort(nums);

        int left = 0, right = 1;
        int result = 0;

        while (left < nums.length && right < nums.length) {
            if (left == right || nums[right] - nums[left] < k) {
                // List item 1 in the text
                right++;
            } else if (nums[right] - nums[left] > k) {
                // List item 2 in the text
                left++;
            } else {
                // List item 3 in the text
                left++;
                result++;
                while (left < nums.length && nums[left] == nums[left - 1])
                    left++;
            }
        }
        return result;
    }
}

class Solution2 {
    public int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        for (int key : map.keySet()) {
            int num1 = key - k;
            int num2 = key + k;

            if (k == 0) {
                if (map.get(key) >= 2) {
                    res += 2; // 因为其他情况都是加了两次, 那这个也加两次
                }
            } else {
                if (map.containsKey(num1)) {
                    res++;
                }
                if (map.containsKey(num2)) {
                    res++;
                }
            }
        }

        return res / 2;
    }
}