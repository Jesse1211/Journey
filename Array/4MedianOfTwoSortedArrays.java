// # Binary Search Solution
// # 他的优点是够快 O(log(min(n, m)))
// # 把两个数组分成两部分, 然后比较两个数组的中间值, 
// # 如果两个数组的中间值相等, 那么就是中位数, 
// # 如果不相等, 那么就把中间值小的那个数组的左半部分和中间值大的那个数组的右半部分合并, 然后再找中间值, 直到找到中位数
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) { // ensure num1 is smaller
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = m;

        while (left <= right) {
            int partitionA = (left + right) / 2; // mid for left
            int partitionB = (m + n + 1) / 2 - partitionA; // mid for right

            int maxLeftA = (partitionA == 0)
                    ? Integer.MIN_VALUE
                    : nums1[partitionA - 1];
            int minRightA = (partitionA == m)
                    ? Integer.MAX_VALUE
                    : nums1[partitionA];
            int maxLeftB = (partitionB == 0)
                    ? Integer.MIN_VALUE
                    : nums2[partitionB - 1];
            int minRightB = (partitionB == n)
                    ? Integer.MAX_VALUE
                    : nums2[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 0) {
                    return ((Math.max(maxLeftA, maxLeftB) +
                            Math.min(minRightA, minRightB)) /
                            2.0);
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                right = partitionA - 1;
            } else {
                left = partitionA + 1;
            }
        }
        return 0.0;
    }
}