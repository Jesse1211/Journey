package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root == null) {
            return res;
        }

        Boolean leftToRight = false;
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int level = q.size();
            for (int i = 0; i < level; i++) {
                var cur = q.poll();
                curLevel.add(cur.val);
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
            }

            if (!leftToRight) {
                Collections.reverse(curLevel);
            }
            res.add(curLevel);
            leftToRight = !leftToRight;
        }
        return res;
    }
}