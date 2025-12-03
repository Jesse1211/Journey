package BinarySearchInclusive;

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length; // 最差情况答案是right+k

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] - (mid + 1) < k) { // mid + 1 = amount of values
                left = mid + 1; // 如果是答案, 也要+1因为最后找的是value instead of index
            } else {
                // 丢的太多了, 没事但是最后答案有可能是这个, 因为找的是value instead of index
                right = mid;
            }
        }

        // left = 丢了的数值
        return left + k;
    }
}