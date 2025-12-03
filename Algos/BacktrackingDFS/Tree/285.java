package BacktrackingDFS.Tree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    TreeNode res;
    TreeNode prev; // always slower than root, when prev == p, root is res

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return res;
    }

    private void inorder(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }

        inorder(root.left, p);

        if (prev == p) {
            res = root;
        }
        prev = root;

        inorder(root.right, p);
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