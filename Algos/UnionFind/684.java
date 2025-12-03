package UnionFind;

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int len) {
        parent = new int[len]; // n-index = parent of node n
        rank = new int[len]; // lower rank, more 'root'

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public boolean union(int i, int j) {
        int iParent = find(i);
        int jParent = find(j);

        if (iParent == jParent) {
            return false; // they have connected
        }

        if (rank[iParent] > rank[jParent]) { // jParent is more 'root' than iParent
            parent[iParent] = jParent;// update jParent
        } else if (iParent < jParent) {
            parent[jParent] = iParent;
        } else {
            // iParent and jParent in the same level
            // promot anyone become the 'root'
            parent[jParent] = iParent;
            rank[iParent]++;
        }

        return true;
    }

    private int find(int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }
}

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[] {};
    }
}