package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * highlight: 最难BFS meta
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>(); // column index : vertical column
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> col = new ArrayDeque<>();
        q.offer(root);
        col.offer(0);

        int leftBound = 0;
        int rightBound = 0;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int curCol = col.poll();

            if (!map.containsKey(curCol)) {
                map.put(curCol, new ArrayList<Integer>());
            }
            map.get(curCol).add(cur.val);

            if (cur.left != null) {
                col.add(curCol - 1);
                q.add(cur.left);
                leftBound = Math.min(leftBound, curCol - 1);
            }

            if (cur.right != null) {
                col.add(curCol + 1);
                q.add(cur.right);
                rightBound = Math.max(rightBound, curCol + 1);
            }
        }

        for (int i = leftBound; i <= rightBound; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}