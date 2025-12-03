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
    TreeNode dummyRoot;

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        dfs(root);
        return dummyRoot;
    }

    public TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode newRoot = dfs(root.left);
        if (newRoot == null) {
            if (dummyRoot == null) {
                dummyRoot = root;
            }
            return root;
        }

        TreeNode right = dfs(root.right);

        root.left = null;
        root.right = null;
        newRoot.right = root;
        newRoot.left = right;
        return root;
    }
}