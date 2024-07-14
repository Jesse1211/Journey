package Backtracking.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        while (!q.isEmpty()) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                var cur = q.poll();
                if (cur.right != null) {
                    childToParent.put(cur.right, cur);
                    q.offer(cur.right);
                }
                if (cur.left != null) {
                    childToParent.put(cur.left, cur);
                    q.offer(cur.left);
                }
            }
        }

        Set<TreeNode> visited = new HashSet<>();
        q.offer(target);
        while (!q.isEmpty() && k > 0) {
            k--;
            int level = q.size();
            for (int i = 0; i < level; i++) {
                var cur = q.poll();
                visited.add(cur);

                if (cur.right != null && !visited.contains(cur.right)) {
                    q.offer(cur.right);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    q.offer(cur.left);
                }
                if (childToParent.containsKey(cur) && !visited.contains(childToParent.get(cur))) {
                    q.offer(childToParent.get(cur));
                }
            }

        }

        while (!q.isEmpty()) {
            res.add(q.poll().val);
        }
        return res;

    }
}