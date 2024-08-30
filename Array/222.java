
/*
 * highlight: 有点抽象
 */

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
    public int countNodes(TreeNode root) {

        int maxDepth = maxDepth(root);
        return countNodes(root, maxDepth - 1);
    }

    private int countNodes(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.right);
        int curLevelNodes = (int) Math.pow(2, left);

        TreeNode next;
        if (left == depth) {
            // left is filled
            next = root.right;
        } else {
            // left is not filled, no need to check right
            next = root.left;
        }

        return curLevelNodes + countNodes(next);
    }

    private int maxDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }
}