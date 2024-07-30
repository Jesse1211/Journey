package Backtracking.BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/*
 * 1. 判定bfs的逻辑: 
*      从任意点出发, 逐层遍历
*      因为graph, 小心重复访问
 * 2. 用Map&queue保存信息:
*      old : new
*      将要访问的点
 * 3. 用queue更新信息:
*      遍历old的neighbors, 通过是否在map中判断是否访问过
*      如果没有访问过, 则建立新的node, 加入map和queue, 把新的node加入new的neighbors
*      如果访问过, 则直接加入new的neighbors
 */

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