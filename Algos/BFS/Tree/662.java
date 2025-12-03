package BFS.Tree;

import java.util.ArrayDeque;
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
    public int widthOfBinaryTree(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> indices = new ArrayDeque<>();
        q.offer(root);
        indices.offer(0);

        while (!q.isEmpty()) {
            int len = q.size();
            int min = 0;
            int max = 0;

            for (int i = 0; i < len; i++) {
                TreeNode cur = q.poll();
                int index = indices.poll();
                if (i == 0) {
                    min = index;
                }

                max = index;

                if (cur.left != null) {
                    q.offer(cur.left);
                    indices.offer(index * 2);
                }

                if (cur.right != null) {
                    q.offer(cur.right);
                    indices.offer(index * 2 + 1);
                }
            }

            res = Math.max(max - min + 1, res);
        }

        return res;
    }
}