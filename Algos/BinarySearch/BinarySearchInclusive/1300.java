package BinarySearchInclusive;

import java.util.Arrays;

// highlight: 纵向 / 横向 比较，找到最小的差值

class Solution {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr[arr.length - 1];
        int minDifference = Integer.MAX_VALUE;
        int result = 0;

        while (left <= right) {
            int middle = (left + right) / 2;
            int sum = sumAfterChanges(arr, middle);
            if (sum == target) {
                return middle;
            } else if (sum > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }

            if ((Math.abs(sum - target) < minDifference)
                    || (Math.abs(sum - target) == minDifference && middle < result)) {
                minDifference = Math.abs(sum - target);
                result = middle;
            }
        }
        return result;
    }

    private int sumAfterChanges(int[] array, int value) {
        int sum = 0;
        for (int number : array) {
            sum += Math.min(number, value);
        }
        return sum;
    }

}

class Solution2 { // 个人喜欢这个写法 因为是exclusive

    public int findBestValue(int[] arr, int target) {
        int n = arr.length;
        int lo = 0, hi = 0;
        for (int i = 0; i < n; i++) {
            hi = Math.max(hi, arr[i]);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.min(arr[i], mid);
            }
            if (sum == target) {
                return mid;
            } else if (sum > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        // need to take care of upper/lower bound
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += Math.min(arr[i], lo);
            sum2 += Math.min(arr[i], lo - 1);
        }

        return Math.abs(sum2 - target) <= Math.abs(sum1 - target) ? lo - 1 : lo;
    }
}