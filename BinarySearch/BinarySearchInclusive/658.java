package BinarySearchInclusive;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            // 找到segment最左侧 - left
            int mid = (left + right) / 2;

            if (x < arr[mid]) { // x 在segment左边
                right = mid;
            } else if (x > arr[mid + k]) { // x 在segment右边
                left = mid + 1;
            } else {
                // x 在segment里面
                if (x - arr[mid] <= arr[mid + k] - x) {
                    // x 距离mid更近, 要向left方向shift
                    right = mid;
                } else {
                    // 反之
                    left = mid + 1;
                }
            }
        }

        List<Integer> res = new ArrayList<>(k);
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }

        return res;
    }
}

class Solution2 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                left = mid + 1;
                break;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 如果arr没有x, left会偏大. 为了匹配, arr[mid] == x也会让left偏大一点
        right = left;
        left = left - 1;
        while (right - left + 1 - 2 < k) {
            if (right >= arr.length) {
                left--;
                continue;
            } else if (left < 0) {
                right++;
                continue;
            }

            // if (Math.abs(arr[left] - x) < Math.abs(arr[right] - x) ||
            // (Math.abs(arr[left] - x) == Math.abs(arr[right] - x) && arr[left] <
            // arr[right])) {
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) { // 可以化简
                left--;
            } else {
                right++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}

class Solution3 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int integer : arr) {
            if (k > 0) {
                minHeap.offer(integer);
                k--;
            } else if (Math.abs(minHeap.peek() - x) > Math.abs(integer - x)) {
                minHeap.poll();
                minHeap.offer(integer);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        return result;
    }
}
