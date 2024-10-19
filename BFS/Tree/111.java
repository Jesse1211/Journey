package BFS.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 和102题的区别: 
 *      用variable res来记录当前的层数
 *      如果当前node没有children, 直接return
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
    public int minDepth(TreeNode root) {
        int res = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root == null) {
            return res;
        }

        q.offer(root);
        while (!q.isEmpty()) {
            res++;
            int level = q.size();
            for (int i = 0; i < level; i++) {
                var cur = q.poll();

                if (cur.right != null) {
                    q.offer(cur.right);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.left == null && cur.right == null) {
                    return res;
                }
            }
        }
        return res;

    }

}