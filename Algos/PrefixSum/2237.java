package PrefixSum;

class Solution {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {

        int[] count = new int[n];
        int rightMost = n - 1;

        for (int[] light : lights) {
            int position = light[0];
            int range = light[1];
            int left = Math.max(0, position - range);
            int right = Math.min(rightMost, position + range);

            count[left]++;

            if (right < rightMost) {
                count[right + 1]--; // the index AFTER the range
            }
        }

        int cur = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            cur += count[i];
            if (cur >= requirement[i]) {
                res++;
            }
        }

        return res;
    }
}