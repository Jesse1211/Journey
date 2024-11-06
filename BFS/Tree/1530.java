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

class Solution1 {
    int res = 0;

    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return res;
    }

    private int[] dfs(TreeNode node, int distance) {
        int[] sum = new int[distance + 1]; // access to chid within i distance

        if (node == null) {
            return sum;
        }

        if (node.left == null && node.right == null) {
            // at distance 1, there is 1 leaf
            sum[1] = 1; // for its parent to count
            return sum;
        }

        int[] left = dfs(node.left, distance);
        int[] right = dfs(node.right, distance);

        // 结算left & right
        int leftDistance = 0;
        while (leftDistance < distance) {
            int rightDistance = distance - leftDistance;
            // 确保i + j <= distance
            while (rightDistance > 0) {
                // 相乘代表一共能组成pair的数量
                res += left[leftDistance] * right[rightDistance];
                rightDistance--;
            }

            leftDistance++;
        }

        // update sum for its parent
        for (int i = 1; i < distance; i++) {
            // 当前通过distance能达到的 = left 和 right 通过distance - 1 能达到的
            sum[i] = left[i - 1] + right[i - 1];
        }

        return sum;
    }
}

class Solution2 {
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