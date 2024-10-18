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