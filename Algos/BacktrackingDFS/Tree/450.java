package BacktrackingDFS.Tree;

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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = bigger(root.right).val;
                root.right = deleteNode(root.right, root.val);
            } else if (root.left != null) {
                root.val = smaller(root.left).val;
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    private TreeNode bigger(TreeNode root) {
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    private TreeNode smaller(TreeNode root) {
        while (root != null && root.right != null) {
            root = root.right;
        }
        return root;
    }
}