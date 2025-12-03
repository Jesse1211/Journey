package PrefixSum;

class Solution {
    public int minGroups(int[][] intervals) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int[] i : intervals) {
            min = Math.min(min, i[0]);
            max = Math.max(max, i[1]);
        }

        int[] freq = new int[max + 1 + 1];

        for (int[] i : intervals) {
            int start = i[0];
            int end = i[1];
            freq[start]++;
            freq[end + 1]--; // inclusive
        }

        int res = 0;
        int sum = 0;
        for (int f : freq) {
            sum += f;
            res = Math.max(res, sum);
        }

        return res;
    }
}