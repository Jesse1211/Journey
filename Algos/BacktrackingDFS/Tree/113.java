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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, res, new ArrayList<>());
        return res;
    }

    private void dfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> cur) {
        if (root == null) {
            return;
        }

        if (targetSum == root.val && root.left == null && root.right == null) {
            cur.add(root.val);
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            return;
        }

        cur.add(root.val);
        dfs(root.left, targetSum - root.val, res, cur);
        dfs(root.right, targetSum - root.val, res, cur);
        cur.remove(cur.size() - 1);

        return;
    }
}