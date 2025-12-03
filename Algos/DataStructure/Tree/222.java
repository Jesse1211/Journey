package DataStructure.Tree;

/*
 * highlight: 通过depth确定左右子树哪个是完全二叉树
 * 完全二叉树: node总数为2^depth
 * 不完全二叉树: node总数要进一步计算
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
        if (root == null) {
            return 0;
        }

        int leftDepth = findDepth(root.left);
        int rightDepth = findDepth(root.right);

        if (leftDepth == rightDepth) {
            // left is complete
            return (int) Math.pow(2, leftDepth) + countNodes(root.right);
        } else {
            // right is complete
            return countNodes(root.left) + (int) Math.pow(2, rightDepth);
        }
    }

    private int findDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }
}