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
        if (root == null) {
            return res;
        }
        dfs(root, res, "");
        return res;
    }

    private void dfs(TreeNode root, List<String> res, String cur) {
        if (root == null) {
            return;
        }

        if (root != null && root.left == null && root.right == null) {
            res.add(cur + root.val);
            return;
        }

        dfs(root.left, res, cur + root.val + "->");
        dfs(root.right, res, cur + root.val + "->");
    }
}