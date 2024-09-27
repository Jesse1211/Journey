package Backtracking.DFS.Tree;

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
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0, 0);
    }

    private int dfs(TreeNode root, int res, int cur) {
        if (root == null) {
            return res;
        }

        if (root != null && root.left == null && root.right == null) {
            res += cur * 10 + root.val;
            return res;
        }

        return dfs(root.left, res, cur * 10 + root.val) + dfs(root.right, res, cur * 10 + root.val);

    }
}