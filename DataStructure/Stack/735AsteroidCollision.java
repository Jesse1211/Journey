package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int cur = asteroids[i];
            if (cur > 0 || stack.isEmpty()) {
                stack.push(cur); // only push positive int
            } else {
                // when find negative int & more "powerful" int: pop "less powerful" positive
                // ints
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(cur)) {
                    stack.pop();
                }
                // handle two same-power int
                if (!stack.isEmpty() && stack.peek() == Math.abs(cur)) {
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(cur);
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}