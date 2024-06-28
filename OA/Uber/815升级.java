import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
// Leetcode 815，除了原题的input之外多加了一个fares，要求从起点到终点的最小费用
// Given a list of buses, bus stops calculate the minimum fare possible in order
// to travel from one particular stop to another particular stop. Like in real
// life we can leave/hop onto a buses on a particular bus stop.
// example
// buses <bus_id, stops> :
// {
// 1 : [2,5,7],
// 2 : [3,6,9],
// 3 : [5,6],
// 4 : [5,9]
// }
// fares <bus_id, fare> :
// {1 :1,
// 2:1,
// 3:1,
// 4:4}
// start_stop : 5, end_stop : 9

// There are 2 routes 1) take bus3 then take bus2 2) take bus4, first route cost
// ‍‍‍‍‌‌‌‍‍‍‍‍‌‌‌‍‍‍‍2, second cost 4, return 2 in this case
class Solution {
    public int numBusesToDestination(Map<Integer, ArrayList<Integer>> busId_stops, int source, int target,
            Map<Integer, Integer> id_fares) {

        Map<Integer, ArrayList<Integer>> stop_busId = new HashMap<>();
        // stop_busId: {bus stop: [avail bus]}
        for (var entry : busId_stops.entrySet()) {
            int busId = entry.getKey();
            for (int stop : entry.getValue()) {
                ArrayList<Integer> buses = stop_busId.getOrDefault(stop, new ArrayList<>());
                buses.add(busId);
                stop_busId.put(stop, buses);
            }
        }

        Set<Integer> visitedBus = new HashSet<>();
        Set<Integer> visitedStop = new HashSet<>();

        int minCost = dfs(busId_stops, stop_busId, source, target, id_fares, visitedBus, visitedStop, 0);
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    private int dfs(Map<Integer, ArrayList<Integer>> busId_stops, Map<Integer, ArrayList<Integer>> stop_busId,
            int source, int target, Map<Integer, Integer> id_fares,
            Set<Integer> visitedBus, Set<Integer> visitedStop, int cost) {

        if (source == target) {
            return cost;
        }

        if (!stop_busId.containsKey(source)) {
            return Integer.MAX_VALUE;
        }

        if (visitedStop.contains(source)) {
            return Integer.MAX_VALUE;
        }

        visitedStop.add(source);

        int minCost = Integer.MAX_VALUE;

        if (stop_busId.get(source) == null) {
            return minCost;
        }

        for (var busId : stop_busId.get(source)) {
            if (visitedBus.contains(busId)) {
                continue;
            }
            visitedBus.add(busId);

            for (int stop : busId_stops.get(busId)) {
                int nextCost = dfs(busId_stops, stop_busId, stop, target, id_fares, visitedBus, visitedStop,
                        cost + id_fares.get(busId));
                minCost = Math.min(minCost, nextCost);
            }

            visitedBus.remove(busId);
        }

        visitedStop.remove(source);

        return minCost;

    }
}