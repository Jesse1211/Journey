package Backtracking.BFS.Tree;

// 有两种解法, 哪种更好? 对于可以BFS的题, 我觉得都行hhhh
// 1. 用递归DFS解法(需要控制level): basecase就是当cur为空时return; inductive case就是append当前node的值到res[level]里面，然后递归左右子树，level+1
// 在这里需要level是因为要把每一个level的node放到一个element里面，所以需要一个level来区分

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (root == null) {
            return res;
        }

        q.add(root);
        while (!q.isEmpty()) {
            int level = q.size();
            List<Integer> subLevels = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                var cur = q.remove();
                subLevels.add(cur.val);
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            res.add(subLevels);
        }
        return res;
    }
}