package BacktrackingDFS.Tree;

import java.util.ArrayList;
import java.util.List;

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

class BSTIterator {
    int index;
    List<Integer> inorder;

    public BSTIterator(TreeNode root) {
        index = 0;
        inorder = new ArrayList<>();
        dfs(root, inorder);
    }

    public int next() {
        return inorder.get(index++);
    }

    public boolean hasNext() {
        return index < inorder.size();
    }

    private void dfs(TreeNode root, List<Integer> inorder) {
        if (root == null) {
            return;
        }

        dfs(root.left, inorder);
        inorder.add(root.val);
        dfs(root.right, inorder);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */