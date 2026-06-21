---
authors:
  - Jesse
title: Union Find
status: pending
source: CodeBlog
---

## O(n \* m)

## 1-D

```JAVA
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
        // same parent
        if (xParent == yParent) return false;
        // different parent
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
}
```
> **Question**
>
> 684, 1101
