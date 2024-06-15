package DP;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int temp = Integer.MIN_VALUE;
        for (var interval : intervals) {
            if (interval[0] < temp) {
                res++;
            } else {
                temp = interval[1];
            }
        }
        return res;
    }
}