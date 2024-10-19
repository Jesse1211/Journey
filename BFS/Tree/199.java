package BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * 和102的区别:
 *     每一层的for loop中, 除了最后一个元素, 都把当前元素的左右子树加入queue
 *      最后一个元素代表当前level的最右侧, 加入res, 然后再把左右子树加入queue
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