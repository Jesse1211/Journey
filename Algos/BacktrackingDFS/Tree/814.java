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

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (!dfs(root)) {
            return null;
        }
        return root;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return false; // false: does not have 1
        }

        boolean left = dfs(root.left);
        boolean right = dfs(root.right);

        if (left == false) {
            root.left = null;
        }

        if (right == false) {
            root.right = null;
        }

        return left || right || root.val == 1;
    }
}