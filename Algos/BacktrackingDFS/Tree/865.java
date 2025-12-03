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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }

        int left = findDepth(root.left);
        int right = findDepth(root.right);

        if (left == right) {
            return root;
        }

        if (left < right) {
            return subtreeWithAllDeepest(root.right);
        }
        return subtreeWithAllDeepest(root.left);

    }

    private int findDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(findDepth(root.left), findDepth(root.right));
    }
}