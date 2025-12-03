package UnionFind;

import java.util.Arrays;

class Solution {
    public class UnionFind {
        int[] parents;
        int[] ranks;
        int groupNum = 0;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }

            groupNum = n;
        }

        public boolean union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);

            if (xParent == yParent)
                return false;
            groupNum--;
            if (ranks[xParent] > ranks[yParent]) {
                parents[yParent] = xParent;
            } else if (ranks[xParent] < ranks[yParent]) {
                parents[xParent] = yParent;
            } else {
                parents[yParent] = xParent;
                ranks[xParent]++;
            }

            return true;
        }

        public int find(int node) {
            while (node != parents[node]) {
                int parentNode = parents[parents[node]];
                parents[node] = parentNode;
                node = parentNode;
            }

            return node;
        }

        public int getGroupNum() {
            return groupNum;
        }
    }

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);

        UnionFind unionFind = new UnionFind(n);

        for (int[] log : logs) {
            unionFind.union(log[1], log[2]);
            if (unionFind.getGroupNum() == 1) {
                return log[0];
            }
        }

        return -1;
    }
}