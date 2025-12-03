import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temp) {
        int n = temp.length;
        int[] res = new int[n];
        Arrays.fill(res, 0);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int cur = temp[i];

            while (!stack.isEmpty() && temp[stack.peek()] < cur) {
                int prev = stack.pop();
                res[prev] = i - prev;
            }

            stack.push(i);
        }

        return res;
    }
}