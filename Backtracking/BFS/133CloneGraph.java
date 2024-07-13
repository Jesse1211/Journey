package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();
        Node res = new Node(node.val);
        q.offer(node);
        map.put(node, res);

        while (!q.isEmpty()) {

            Node cur = q.poll();
            Node newCur = map.get(cur);

            for (var each : cur.neighbors) {
                if (!map.containsKey(each)) {
                    map.put(each, new Node(each.val));
                    q.offer(each);
                }
                newCur.neighbors.add(map.get(each));
            }
        }
        return res;
    }
}