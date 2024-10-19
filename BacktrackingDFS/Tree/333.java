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

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return countNode(root);
        }

        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    private boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    private int countNode(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return countNode(root.left) + countNode(root.right) + 1;
    }
}