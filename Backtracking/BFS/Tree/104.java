package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 和102很像, 每层不需要存到res里, 只需要increment by 1
 */

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
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            res++;
            int len = q.size();
            for (int i = 0; i < len; i++) {
                TreeNode cur = q.poll();
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }

            }
        }
        return res;
    }
}