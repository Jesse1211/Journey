package 两个array;

import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int greedIndex = 0;
        int sizeIndes = 0;
        int res = 0;
        while (greedIndex < g.length && sizeIndes < s.length) {
            if (g[greedIndex] <= s[sizeIndes]) { // satisfied
                res++;
                greedIndex++;
                sizeIndes++;
            } else { // try use bigger one
                sizeIndes++;
            }
        }
        return res;
    }
}