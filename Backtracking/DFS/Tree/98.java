package Tree;

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

/*
 * highlight: 这个max和min的保存方式很聪明
 */

class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        } else if (root.val >= max || root.val <= min) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
}