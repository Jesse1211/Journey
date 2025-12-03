package DataStructure.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        for (int current = n - 1; current >= 0; --current) {
            while (!stack.isEmpty() && heights[stack.peek()] < heights[current]) {
                // Make stack's top > cur
                stack.pop();
            }

            if (stack.isEmpty()) {
                list.add(current);
            }
            stack.push(current);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(list.size() - 1 - i);
        }

        return answer;
    }
}