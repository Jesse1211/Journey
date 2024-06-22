package TwoPointer;

import java.util.Arrays;

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        Arrays.sort(nums4);
        int size = nums1.length;
        int res = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int compl = 0 - nums1[i] - nums2[j];
                int left = 0;
                int right = size - 1;

                while (left < size && right >= 0) {
                    int cur = nums3[left] + nums4[right];
                    if (cur == compl) {
                        int leftRes = 1;
                        int rightRes = 1;
                        while (left + 1 < size && nums3[left] == nums3[left + 1]) {
                            left++;
                            leftRes++;
                        }
                        while (right - 1 >= 0 && nums4[right] == nums4[right - 1]) {
                            right--;
                            rightRes++;
                        }
                        res += rightRes * leftRes;
                        right--;
                        left++;

                    } else if (cur < compl) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}