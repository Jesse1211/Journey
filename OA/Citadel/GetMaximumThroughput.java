package OA.Citadel;

import java.util.List;
import java.util.PriorityQueue;

class GetMaximumThroughput {

    public static int getMaximumThroughput(List<Integer> throughput, List<Integer> scaling_cost, int budget) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] * a[2] - b[1] * b[2]); // index, throughput, times

        for (int i = 0; i < throughput.size(); i++) {
            q.add(new int[] { i, throughput.get(i), 1 });
        }

        long minThroughput = Integer.MAX_VALUE;
        while (!q.isEmpty() && budget > 0) {
            int[] cur = q.poll();
            System.out.println("index:" + cur[0] + "------ throughput: " + cur[1] + "-------- times: " + cur[2]);

            int cost = scaling_cost.get(cur[0]);

            if (budget < cost) {
                minThroughput = Math.min(minThroughput, cur[1] * cur[2]);
                continue;
            }

            budget -= cost;

            q.offer(new int[] { cur[0], cur[1], cur[2] + 1 });
        }

        return (int) minThroughput;
    }

    public static void main(String[] args) {
        int budget = 28;
        List<Integer> throughput = List.of(3, 2, 5);
        List<Integer> scaling_cost = List.of(2, 5, 10);
        System.out.println(getMaximumThroughput(throughput, scaling_cost, budget));
        // res = 6

        budget = 25;
        throughput = List.of(7,3,4,6);
        scaling_cost = List.of(2,5,4,3);
        System.out.println(getMaximumThroughput(throughput, scaling_cost, budget));
        // res = 9
    }

}


