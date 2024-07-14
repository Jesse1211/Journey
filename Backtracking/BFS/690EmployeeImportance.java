package Backtracking.BFS;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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