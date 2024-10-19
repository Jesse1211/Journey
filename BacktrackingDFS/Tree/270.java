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
/// 可以用inorder

class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        return dfs(root, target);
    }

    private int dfs(TreeNode root, double target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (target < root.val) {
            int left = dfs(root.left, target);
            if (Math.abs(left - target) < Math.abs(root.val - target)) {
                return left;
            } else if (Math.abs(left - target) == Math.abs(root.val - target)) {
                return Math.min(left, root.val);
            }
            return root.val;
        }

        int right = dfs(root.right, target);
        if (Math.abs(right - target) < Math.abs(root.val - target)) {
            return right;
        } else if (Math.abs(right - target) == Math.abs(root.val - target)) {
            return Math.min(right, root.val);
        }
        return root.val;
    }
}