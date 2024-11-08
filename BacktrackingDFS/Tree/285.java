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
    TreeNode prev;
    TreeNode res;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return null;

        dfs(root, p);
        return res;
    }

    private void dfs(TreeNode root, TreeNode target) {
        if (root == null) {
            return;
        }

        dfs(root.left, target);

        if (prev != null && prev == target) {
            res = root;
            prev = null; // 之后再也不会进来这个if了
        }

        prev = root;
        dfs(root.right, target);
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