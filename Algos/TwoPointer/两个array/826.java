package 两个array;

import java.util.Arrays;

class Solution {

    class Assignment {
        int profit;
        int difficulty;

        Assignment(int p, int d) {
            profit = p;
            difficulty = d;
        }
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Assignment[] assignments = new Assignment[n];
        for (int i = 0; i < n; i++) {
            assignments[i] = new Assignment(profit[i], difficulty[i]);
        }

        Arrays.sort(assignments, (a, b) -> a.difficulty - b.difficulty);
        Arrays.sort(worker);

        int maxProfit = 0;
        int profitSoFar = 0;
        int index = 0;
        for (int work : worker) {
            while (index < assignments.length && assignments[index].difficulty <= work) {
                // one job can be completed multiple times.
                profitSoFar = Math.max(profitSoFar, assignments[index].profit);
                index++;
            }
            maxProfit += profitSoFar;
        }
        return maxProfit;
    }
}