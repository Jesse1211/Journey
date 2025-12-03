package BFS.Tree;

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

        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> col = new ArrayDeque<>();

        Map<Integer, List<Integer>> map = new HashMap<>(); // index: list of node value
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        q.offer(root);
        col.offer(0);

        while (!q.isEmpty()) {

            TreeNode cur = q.poll();
            int curIndex = col.poll();

            min = Math.min(min, curIndex);
            max = Math.max(max, curIndex);
            if (!map.containsKey(curIndex)) {
                map.put(curIndex, new ArrayList<>());
            }
            map.get(curIndex).add(cur.val);

            if (cur.left != null) {
                q.offer(cur.left);
                col.offer(curIndex - 1);
            }

            if (cur.right != null) {
                q.offer(cur.right);
                col.offer(curIndex + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;

    }
}