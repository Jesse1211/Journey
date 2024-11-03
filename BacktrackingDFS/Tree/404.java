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

class Solution2 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            left = root.left.val;
        }

        return left + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root, false);
    }

    private int dfs(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }

        if (isLeft) {
            if (root.left == null && root.right == null) {
                return root.val;
            }
        }

        return dfs(root.left, true) + dfs(root.right, false);
    }
}