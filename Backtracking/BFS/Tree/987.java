package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> map = new HashMap<>(); // index: list of values from top to bot
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> col = new ArrayDeque<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        q.offer(root);
        col.offer(0);

        while (!q.isEmpty()) {
            int level = q.size();

            Map<Integer, List<Integer>> curMap = new HashMap<>(); // index: list of values from top to bot

            for (int i = 0; i < level; i++) {
                TreeNode cur = q.poll();
                int index = col.poll();

                if (!curMap.containsKey(index)) {
                    curMap.put(index, new ArrayList<>());
                }
                curMap.get(index).add(cur.val);

                max = Math.max(max, index);
                min = Math.min(min, index);

                if (cur.left != null) {
                    q.offer(cur.left);
                    col.offer(index - 1);
                }

                if (cur.right != null) {
                    q.offer(cur.right);
                    col.offer(index + 1);
                }
            }

            for (var entry : curMap.entrySet()) {
                List<Integer> curList = entry.getValue();
                Collections.sort(curList);

                map.putIfAbsent(entry.getKey(), new ArrayList<>());
                map.get(entry.getKey()).addAll(curList);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}