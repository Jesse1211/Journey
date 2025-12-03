package BacktrackingDFS.Tree;

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

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return res;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = dfs(root.left);
        boolean right = dfs(root.right);

        if (left == right && left == true) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }

            if (root.right != null && root.right.val != root.val) {
                return false;
            }

            // now left & right have same val to root
            res++;
            return true;
        }

        return false;
    }
}