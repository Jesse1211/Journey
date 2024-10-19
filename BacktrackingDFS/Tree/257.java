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

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, res, "");
        return res;
    }

    private void dfs(TreeNode root, List<String> res, String cur) {
        if (root == null) {
            return;
        }

        if (cur.length() != 0) {
            cur += "->";
        }
        cur += root.val;

        if (root.left == null && root.right == null) {
            res.add(cur);
            return;
        }

        dfs(root.left, res, cur);
        dfs(root.right, res, cur);
    }
}