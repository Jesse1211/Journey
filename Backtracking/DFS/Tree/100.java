package Tree;

/* 
 * 1. 判定dfs的parameters: p, q
*      (这里只是比较, 所以不需要res)
*      当前candidates取决于p.val, q.val
*      层层比较p.val, q.val
 * 2. dfs 何时停止: p和q都为null, 或者p.val != q.val, 或者只有p or q为null
 * 3. dfs 如何更新: p.val == q.val时, dfs(p.left, q.left) && dfs(p.right, q.right)
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return q == null && p == null;
        } else if (q.val == p.val) {
            return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
        } else {
            return false;
        }
    }
}