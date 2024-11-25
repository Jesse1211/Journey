package UnionFind;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {

        HashMap<String, Pair<String, Double>> gidWeight = new HashMap<>();

        // Step 1). build the union groups
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            union(gidWeight, dividend, divisor, quotient);
        }

        // Step 2). run the evaluation, with "lazy" updates in find() function
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor))
                // case 1). at least one variable did not appear before
                results[i] = -1.0;
            else {
                Pair<String, Double> dividendEntry = find(gidWeight, dividend);
                Pair<String, Double> divisorEntry = find(gidWeight, divisor);

                String dividendGid = dividendEntry.getKey();
                String divisorGid = divisorEntry.getKey();
                Double dividendWeight = dividendEntry.getValue();
                Double divisorWeight = divisorEntry.getValue();

                if (!dividendGid.equals(divisorGid))
                    // case 2). the variables do not belong to the same chain/group
                    results[i] = -1.0;
                else
                    // case 3). there is a chain/path between the variables
                    results[i] = dividendWeight / divisorWeight;
            }
        }

        return results;
    }

    private Pair<String, Double> find(HashMap<String, Pair<String, Double>> gidWeight, String nodeId) {
        if (!gidWeight.containsKey(nodeId))
            gidWeight.put(nodeId, new Pair<String, Double>(nodeId, 1.0));

        Pair<String, Double> entry = gidWeight.get(nodeId);
        // found inconsistency, trigger chain update
        if (!entry.getKey().equals(nodeId)) {
            Pair<String, Double> newEntry = find(gidWeight, entry.getKey());
            gidWeight.put(nodeId, new Pair<String, Double>(
                    newEntry.getKey(), entry.getValue() * newEntry.getValue()));
        }

        return gidWeight.get(nodeId);
    }

    private void union(HashMap<String, Pair<String, Double>> gidWeight, String dividend, String divisor, Double value) {
        Pair<String, Double> dividendEntry = find(gidWeight, dividend);
        Pair<String, Double> divisorEntry = find(gidWeight, divisor);

        String dividendGid = dividendEntry.getKey();
        String divisorGid = divisorEntry.getKey();
        Double dividendWeight = dividendEntry.getValue();
        Double divisorWeight = divisorEntry.getValue();

        // merge the two groups together,
        // by attaching the dividend group to the one of divisor
        if (!dividendGid.equals(divisorGid)) {
            gidWeight.put(dividendGid, new Pair<String, Double>(divisorGid,
                    divisorWeight * value / dividendWeight));
        }
    }
}

class Solution2 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>(); // left: {right: val}

        for (int i = 0; i < equations.size(); i++) {
            double val = values[i];
            String left = equations.get(i).get(0); // left / right = val
            String right = equations.get(i).get(1);
            if (!map.containsKey(left)) {
                map.put(left, new HashMap<>());
            }

            map.get(left).put(right, val);

            if (!map.containsKey(right)) {
                map.put(right, new HashMap<>());
            }

            map.get(right).put(left, 1 / val);
        }

        double[] res = new double[queries.size()];
        Arrays.fill(res, -1);

        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String left = query.get(0);
            String right = query.get(1);

            if (!map.containsKey(left) || !map.containsKey(right)) {
                continue;
            } else if (left.equals(right)) {
                res[i] = 1.0;
                continue;
            }

            Set<String> visited = new HashSet<>();
            Queue<Pair<String, Double>> q = new ArrayDeque<>(); // string, cost
            q.offer(new Pair(left, 1.0));
            visited.add(left);
            while (!q.isEmpty()) {
                Pair<String, Double> cur = q.poll();
                String s = cur.getKey();
                double val = cur.getValue();

                for (String key : map.get(s).keySet()) {
                    if (visited.contains(key)) {
                        continue;
                    }

                    visited.add(key);
                    if (key.equals(right)) {
                        res[i] = map.get(s).get(key) * val;
                        q.clear();
                        break;
                    }
                    q.offer(new Pair(key, map.get(s).get(key) * val));
                }

            }
        }
        return res;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}