package BacktrackingDFS.Tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    int res = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 1);
        return res;
    }

    private void dfs(TreeNode root, int len) {
        if (root == null) {
            res = Math.max(res, len);
            return;
        }

        res = Math.max(res, len);

        if (root.left != null) {
            if (root.left.val - 1 == root.val) {
                dfs(root.left, len + 1);
            } else {
                dfs(root.left, 1);
            }
        }

        if (root.right != null) {
            if (root.right.val - 1 == root.val) {
                dfs(root.right, len + 1);
            } else {
                dfs(root.right, 1);
            }
        }
        return;

    }
}