package BacktrackingDFS.Tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    TreeNode pre;
    TreeNode res;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        helper(root, p);
        return res;
    }

    private void helper(TreeNode root, TreeNode target) {
        if (root == null)
            return;

        helper(root.left, target);

        if (pre == target) {
            res = root;
            // 不要忘记给pre赋值，不然之后每次都能match
            pre = root;
            return;
        }
        pre = root;

        helper(root.right, target);
    }
}

class Solution2 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}