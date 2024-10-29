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
    int res = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);

        return res;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        if (left && right) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }

            if (root.right != null && root.right.val != root.val) {
                return false;
            }

            res++;
            return true;
        }

        return false;
    }
}