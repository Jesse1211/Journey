package TwoArrays;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();

        int left = 0;
        int right = 0;

        while (left < firstList.length && right < secondList.length) {
            int leftStart = firstList[left][0];
            int leftEnd = firstList[left][1];
            int rightStart = secondList[right][0];
            int rightEnd = secondList[right][1];

            int newStart = Math.max(leftStart, rightStart);
            int newEnd = Math.min(leftEnd, rightEnd);
            // there is overlapped
            if (newStart <= newEnd) {
                list.add(new int[] { newStart, newEnd });
            }

            if (leftEnd < rightEnd) {
                left++;
            } else {
                right++;
            }
        }

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = new int[] { list.get(i)[0], list.get(i)[1] };
        }

        return res;
    }
}