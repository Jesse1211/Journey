package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * 和102的区别: 这个是chilren, 需要一个for loop来把每个children加到queue
 */

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int level = q.size();
            List<Integer> arr = new ArrayList<>();

            for (int i = 0; i < level; i++) {
                Node cur = q.poll();
                arr.add(cur.val);
                if (cur.children != null) {
                    for (Node each : cur.children) {
                        q.offer(each);
                    }
                }
            }
            res.add(arr);
        }
        return res;
    }
}