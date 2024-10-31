package TwoPointer.两个array;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int left = 0;
        int right = 0;

        while (left < A.length && right < B.length) {
            int start = Math.max(A[left][0], B[right][0]);
            int end = Math.min(A[left][1], B[right][1]);

            if (start <= end) {
                res.add(new int[] { start, end });
            }

            if (A[left][1] < B[right][1]) {
                left++;
            } else {
                right++;
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}