package DataStructure.Map;

import java.util.HashMap;
/*
 * highlight: 活用map
 */

// class Solution {
//     public boolean judgeSquareSum(int c) {
//         long min = 0;
//         long max = (long) Math.sqrt(c);
//         while (min <= max) {
//             long cur = min * min + max * max;
//             if (cur == c) {
//                 return true;
//             } else if (cur < c) {
//                 min++;
//             } else {
//                 max--;
//             }
//         }
//         return false;
//     }
// }

class Solution {
    public boolean judgeSquareSum(int c) {
        HashMap<Integer, Integer> ump = new HashMap<>();

        for (int i = 0; i <= Math.sqrt(c); i++) {
            int square = i * i;
            ump.put(square, ump.getOrDefault(square, 0) + 1);
            int req = c - square;

            if (ump.containsKey(req)) {
                return true;
            }
        }

        return false;
    }
}