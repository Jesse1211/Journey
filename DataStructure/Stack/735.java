package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int cur : asteroids) {
            if (stack.isEmpty()) {
                stack.push(cur);
                continue;
            }

            boolean addCur = true;
            while (!stack.isEmpty() && (stack.peek() > 0 && cur < 0)) {
                if (Math.abs(stack.peek()) < Math.abs(cur)) {
                    stack.pop();
                } else if (Math.abs(stack.peek()) == Math.abs(cur)) {
                    stack.pop();
                    addCur = false;
                    break;
                } else if (Math.abs(stack.peek()) > Math.abs(cur)) {
                    addCur = false;
                    break;
                }
            }
            if (addCur) {
                stack.push(cur);
            }
        }

        int[] res = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }
        return res;
    }
}