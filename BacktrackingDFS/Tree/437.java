package BacktrackingDFS.Tree;

import java.util.HashMap;
import java.util.Map;

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
    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        preorder(root, 0, targetSum, new HashMap<>());
        return count;
    }

    public void preorder(TreeNode node, long cur, int targetSum, Map<Long, Integer> h) {
        if (node == null)
            return;

        cur += node.val;

        if (cur == targetSum)
            count++;

        count += h.getOrDefault(cur - targetSum, 0);
        h.put(cur, h.getOrDefault(cur, 0) + 1);

        preorder(node.left, cur, targetSum, h);
        preorder(node.right, cur, targetSum, h);

        h.put(cur, h.get(cur) - 1); // 按时删除保证不会add up不相连的点
    }

}

// TOO SLOW!
class Solution2 {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        return pathSum(root.left, targetSum) +
                pathSum(root.right, targetSum) +
                (int) dfs(root, targetSum);
    }

    private long dfs(TreeNode root, long cur) {
        if (root == null) {
            return 0;
        }

        long res = 0;

        if (cur == root.val) {
            res++;
        }

        return res +
                dfs(root.left, cur - root.val) +
                dfs(root.right, cur - root.val);
    }
}