import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        // map: {bus stop: [avail bus]}
        for (int i = 0; i < routes.length; i++) { // bus
            for (int j = 0; j < routes[i].length; j++) { // bus stop
                int stop = routes[i][j];
                ArrayList<Integer> buses = map.getOrDefault(stop, new ArrayList<>());
                buses.add(i);
                map.put(stop, buses);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visitedBus = new HashSet<>();
        Set<Integer> visitedStop = new HashSet<>();

        int res = 0;
        q.add(source);
        visitedStop.add(source);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int curStop = q.poll();
                if (curStop == target) {
                    return res;
                }

                if (!map.containsKey(curStop)) {
                    continue;
                }
                ArrayList<Integer> buses = map.get(curStop);
                for (int bus : buses) {
                    if (visitedBus.contains(bus)) {
                        continue;
                    }
                    for (int stop : routes[bus]) {
                        if (visitedStop.contains(stop)) {
                            continue;
                        }
                        q.add(stop);
                        visitedStop.add(stop);
                    }
                    visitedBus.add(bus);
                }
            }
            res++;
        }
        return -1;

    }
}