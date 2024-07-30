package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root == null) {
            return res;
        }

        q.offer(root);
        while (!q.isEmpty()) {

            int level = q.size();
            for (int i = 0; i < level - 1; i++) {
                var cur = q.poll();

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }

            var rightMost = q.poll();

            if (rightMost.left != null) {
                q.offer(rightMost.left);
            }
            if (rightMost.right != null) {
                q.offer(rightMost.right);
            }
            res.add(rightMost.val);
        }

        return res;
    }
}