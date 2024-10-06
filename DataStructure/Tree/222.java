package DataStructure.Tree;

/*
 * highlight: 难崩
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
        int res = 0;
        int maxDepth = maxDepth(root);
        return countNodes(root, maxDepth - 1);
    }

    private int countNodes(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }

        // 拿到root右child的最深level
        int left = maxDepth(root.right);

        // 当前level最多node数量
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