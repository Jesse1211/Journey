package TwoPointer;

import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int greed = 0;
        int size = 0;
        int res = 0;
        while (greed < g.length && size < s.length) {
            if (g[greed] <= s[size]) {
                res++;
                greed++;
                size++;
            } else {
                size++;
            }
        }
        return res;
    }
}