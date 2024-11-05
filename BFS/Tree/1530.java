package BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int countPairs(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        Set<TreeNode> leaves = new HashSet<>();
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(root, null, leaves, graph);

        int res = 0;
        // traverse from leaf
        for (TreeNode leaf : leaves) {
            Queue<TreeNode> q = new ArrayDeque<>();
            Set<TreeNode> visited = new HashSet<>();
            q.offer(leaf);
            visited.add(leaf);

            int maxDepth = distance;
            while (!q.isEmpty() && maxDepth > 0) {
                int level = q.size();
                while (level-- > 0) {

                    TreeNode cur = q.poll();
                    for (TreeNode nei : graph.get(cur)) {
                        if (visited.contains(nei)) {
                            continue;
                        }
                        visited.add(nei);

                        if (leaves.contains(nei) && nei != leaf) {
                            res++;
                        }

                        q.offer(nei);
                    }
                }
                maxDepth--;
            }
        }
        return res / 2;
    }

    private void dfs(TreeNode root, TreeNode prev, Set<TreeNode> leaf, Map<TreeNode, List<TreeNode>> graph) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            leaf.add(root);
        }

        if (prev != null) {
            if (!graph.containsKey(prev)) {
                graph.put(prev, new ArrayList<>());
            }
            graph.get(prev).add(root);

            if (!graph.containsKey(root)) {
                graph.put(root, new ArrayList<>());
            }
            graph.get(root).add(prev);
        }

        dfs(root.left, root, leaf, graph);
        dfs(root.right, root, leaf, graph);
    }
}