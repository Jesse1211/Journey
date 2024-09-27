package Backtracking.DFS.Tree;
/*
 * 1. 判定dfs的parameters: root
*      (这里只是invert, 所以不需要res)
*      当前candidates取决于root.left, root.right
*      层层invert root.left, root.right
 * 2. dfs 何时停止: root == null
 * 3. dfs 如何更新: 
*      先把当前root的left和right交换, 然后再dfs
*      invertTree(root.left), invertTree(root.right)
 * 
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        } else {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.right);
            invertTree(root.left);
            return root;
        }
    }
}