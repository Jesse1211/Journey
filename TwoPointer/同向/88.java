package 同向;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        int index = m + n - 1;

        while (right >= 0) {

            if (left >= 0 && nums1[left] >= nums2[right]) {
                nums1[index] = nums1[left];
                left--;
            } else {
                nums1[index] = nums2[right];
                right--;
            }

            index--;
        }
    }
}