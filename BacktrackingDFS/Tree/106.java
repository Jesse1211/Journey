package BacktrackingDFS.Tree;

import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode helper(
            int[] postorder,
            int inStart,
            int inEnd,
            int postStart,
            int postEnd) {

        if (inStart > inEnd || postStart < 0) {
            return null;
        }

        int rootIndex = map.get(postorder[postEnd]);
        TreeNode root = new TreeNode(postorder[postEnd]);

        root.left = helper(postorder,
                inStart,
                rootIndex - 1,
                postStart,
                postStart + rootIndex - inStart - 1);

        root.right = helper(postorder,
                rootIndex + 1,
                inEnd,
                postStart + rootIndex - inStart,
                postEnd - 1);

        return root;
    }
}