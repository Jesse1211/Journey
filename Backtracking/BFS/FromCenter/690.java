package Backtracking.BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* 
 * 1. 判定bfs的逻辑:
*      从id出发, 遍历所有的subordinates
 * 2. 用Map&queue保存信息:
*      map: id : data 为了更快找到对应的data
*      queue: 保存将要访问的Employee
 * 3. 用queue更新信息:
*      更新 res += cur.importance
*      遍历当前Employee的subordinates, 把subordinates加入queue
*      通过map找到对应的data, 并且加入importance
 */

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>(); // id : data

        for (var em : employees) {
            map.put(em.id, em);
        }

        int res = 0;
        Queue<Integer> q = new ArrayDeque<>(); // track by Ids
        q.offer(id);

        while (!q.isEmpty()) {
            var cur = map.get(q.poll());
            res += cur.importance;

            for (var i : cur.subordinates) {
                q.offer(i);
            }
        }
        return res;
    }
}