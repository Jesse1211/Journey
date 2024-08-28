package Tree;

/*
 * highlight: 这个有点难, 必须从base 开始思考
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public void flatten(TreeNode root) {
        
        if( root != null ){

            // DFS travesal to next level
            flatten( root.right );
            flatten( root.left );

            // flattern binary tree to right skewed linked list
            root.right = prev;
            root.left = null;
            prev = root;
        }
        
        return;        
    }
    
    // record of node of previous traversal
    private TreeNode prev = null;
}