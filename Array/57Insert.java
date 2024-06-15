package Array;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];
        List<Integer[]> list = new ArrayList<Integer[]>();
        for (var interval : intervals) {
            int curS = interval[0];
            int curE = interval[1];
            if (curE < start) {
                // 还没添加 - 无脑抄
                list.add(new Integer[] { curS, curE });
            } else if (end < curS) {
                // 添加了, 把start & end加进来
                list.add(new Integer[] { start, end });
                start = curS;
                end = curE;
            } else {
                // 存在overlap, 不用添加, 更新就好了
                start = Math.min(start, curS);
                end = Math.max(end, curE);
            }
        }
        list.add(new Integer[] { start, end });
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}