package Tree;

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

/*
 * highlight: 基本功要会
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null) {
            return null;
        }
        if (n1.val < root.val && n2.val < root.val) {
            return lowestCommonAncestor(root.left, n1, n2);
        }
        if (n1.val > root.val && n2.val > root.val) {
            return lowestCommonAncestor(root.right, n1, n2);
        }
        return root;
    }
}