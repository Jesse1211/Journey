package BFS.Tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/*
 * 和102的区别: track一共走了多少个level
 *      如果queue是空的, 那就说明没有下一层了, 返回res
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
    public int maxDepth(Node root) {
        int res = 0;
        Queue<Node> q = new ArrayDeque<>();
        if (root == null) {
            return res;
        }

        q.offer(root);
        while (!q.isEmpty()) {
            res++;
            int level = q.size();
            for (int i = 0; i < level; i++) {
                var cur = q.poll();

                if (cur.children != null) {
                    for (var each : cur.children) {
                        q.offer(each);
                    }
                }

            }
        }
        return res;

    }

}