package PrefixSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int brightestPosition(int[][] lights) {
        List<int[]> list = new ArrayList<>(); // [position, count]
        for (int[] light : lights) {
            list.add(new int[] { light[0] - light[1], 1 });
            list.add(new int[] { light[0] + light[1] + 1, -1 });
        }

        Collections.sort(list, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // 一个位置会有多个count, 我们要把他们加起来才能代表当前位置的count
        // 要做increase sort这样可以避免 ‘假性’ count
        // 比如+1, +1, +1, -1 会导致误以为当前位置是3
        // 但是-1, +1, +1, +1 就能确保正确性

        int res = 0;
        int maxPosition = Integer.MIN_VALUE;
        int cur = 0;
        for (int[] i : list) {
            cur += i[1];
            if (cur > res) {
                res = cur;
                maxPosition = i[0];
            }
        }

        return maxPosition;
    }
}