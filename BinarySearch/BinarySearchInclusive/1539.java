package BinarySearchInclusive;

class Solution {
    public int findKthPositive(int[] arr, int k) {

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == mid + 1) { // left is all good
                left = mid + 1;
            } else {
                int diff = arr[mid] - (mid + 1); // 4 - 3 = 1 missing
                if (diff < k) {
                    // 丢的太少了
                    left = mid + 1;
                } else {
                    // 丢的太多了
                    right = mid - 1;
                }
            }
        }
        // left = 丢了的数值
        return left + k;
    }
}