package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int[] res = new int[heights.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = heights.length - 1; i >= 0; i--) {
            int cur = heights[i];
            if (stack.isEmpty()) {
                res[i] = 0;
                stack.push(cur);
                continue;
            }

            int count = 0;
            while (!stack.isEmpty() && stack.peek() < cur) {
                count++;
                stack.pop();
            }

            if (stack.isEmpty()) {
                res[i] = count;
            } else {
                res[i] = count + 1; // count the 'tall' guy
            }

            stack.push(cur);
        }

        return res;
    }
}