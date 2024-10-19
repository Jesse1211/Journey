package BFS.Tree;

/*
 * 1. 判断bfs逻辑:
 *      先从root开始, 层层遍历
 * 2. 用queue & list保存信息:
 *      queue: 保存将要访问的点
 *      List: 保存每一层的点
 * 3. 用queue更新信息:
 *      从root开始, 遍历所有相邻的点, 把符合条件的点加入queue
 */

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