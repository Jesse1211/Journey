import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) {
                res.add(interval);
            } else {
                int[] last = res.getLast();
                last[1] = Math.max(
                        last[1],
                        interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
