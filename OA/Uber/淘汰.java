// 给一个n * m 的array of arrays，每行都是该场比赛结果。排出每轮的淘汰人的名单，如果分数一样就按字母顺序淘汰。
//  这里要用历史最好数据。

// [
//     ["harold 149", "bob 398", "gina 70", "ale 109"], - 淘汰bob，最慢
//     ["harold 60", "bob 29", "gina 98", "ale 70"], - gina和ale历史最好分数一样，按字母顺序淘汰ale，再gina
//     ["harold 149", "bob 398", "gina 98", "ale 109"], - 最后剩下harold
// ]
// 返回["bob", "ale", "gina", "harold"]
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] findElimination(String[][] results) {
        Map<String, Integer> historicalBest = new HashMap<>();
        List<String> res = new ArrayList<>();

        // Update the historical best scores for each participant
        for (String[] round : results) {
            List<String> names = new ArrayList<>();
            int slowest = Integer.MIN_VALUE;

            for (String entry : round) {
                String[] parts = entry.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);

                if (historicalBest.containsKey(name) && historicalBest.get(name) == -1) {
                    continue;
                }
                historicalBest.put(name, Math.min(historicalBest.getOrDefault(name, score), score));

                int bestScore = historicalBest.get(name);

                if (bestScore > slowest) {
                    slowest = bestScore;
                    names = new ArrayList<>();
                    names.add(name);
                } else if (bestScore == slowest) {
                    names.add(name);
                }
            }

            Collections.sort(names);
            for (var name : names) {
                historicalBest.put(name, -1);
                res.add(name);
            }
        }

        return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[][] results = {
                { "harold 149", "bob 398", "gina 70", "ale 109" },
                { "harold 60", "bob 29", "gina 98", "ale 70" },
                { "harold 149", "bob 398", "gina 98", "ale 109" }
        };

        String[] eliminationOrder = solution.findElimination(results);

        // Print the elimination order
        for (String name : eliminationOrder) {
            System.out.println(name);
        }
    }
}
