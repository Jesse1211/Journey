package DataStructure.Map;

import java.util.PriorityQueue;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0])); // value, listIndex, valIndex

        // start from 0 index in every list
        for (int i = 0; i < matrix.length; i++) {
            pq.add(new int[] { matrix[i][0], i, 0 });
        }

        while (!pq.isEmpty() && k-- > 0) {
            int[] cur = pq.poll();
            if (k == 0) {
                return cur[0];
            }
            if (cur[2] == matrix[0].length - 1) {
                // used all in current list
                continue;
            }

            int listIndex = cur[1];
            int valIndex = cur[2];
            int newVal = matrix[listIndex][valIndex + 1];
            pq.offer(new int[] { newVal, listIndex, valIndex + 1 });
        }

        return -1;
    }
}