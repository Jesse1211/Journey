package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/* highlight: tree的bfs最难吧
 * 1. 判断bfs逻辑:
 *      先从root开始, 层层遍历
 * 2. 用queue & int保存信息:
 *      queue: 保存将要访问的点
 *      int: 保存每一层的最左侧的点
 * 3. 用queue更新信息:
 *      从root开始, 遍历所有相邻的点, 把符合条件的点加入queue
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
    public int findBottomLeftValue(TreeNode root) {
        int res = root.val;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode cur = q.poll();
                if (i == 0) {
                    res = cur.val;
                }

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return res;
    }
}