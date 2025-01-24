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
        Queue<Integer> col = new ArrayDeque<>();
        q.offer(root);
        col.offer(0);

        while (!q.isEmpty()) {
            int size = q.size();
            int leftMostIndex = -1;
            int rightMostIndex = -1;

            while (size-- > 0) {
                TreeNode cur = q.poll();
                int index = col.poll();

                if (leftMostIndex == -1) {
                    leftMostIndex = index;
                }
                rightMostIndex = index;

                if (cur.left != null) {
                    q.offer(cur.left);
                    col.offer(index * 2);
                }

                if (cur.right != null) {
                    q.offer(cur.right);
                    col.offer(index * 2 + 1);
                }
            }

            res = Math.max(res, rightMostIndex - leftMostIndex + 1);
        }

        return res;
    }
}