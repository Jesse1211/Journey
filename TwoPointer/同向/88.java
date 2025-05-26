package 同向;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        int index = nums1.length - 1;

        while (left >= 0 && right >= 0 && index >= 0) {
            if (nums1[left] <= nums2[right]) {
                nums1[index] = nums2[right];
                right--;
            } else {
                nums1[index] = nums1[left];
                left--;
            }

            index--;
        }

        while (left >= 0) {
            nums1[index] = nums1[left];
            index--;
            left--;
        }

        while (right >= 0) {
            nums1[index] = nums2[right];
            index--;
            right--;
        }
    }
}