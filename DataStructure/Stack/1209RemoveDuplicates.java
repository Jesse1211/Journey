package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>(); // {index, amount}
        for (int i = 0; i < s.length(); i++) {
            if (stack.size() > 0 && s.charAt(stack.peek()[0]) == s.charAt(i)) {
                stack.peek()[1]++;
            } else {
                stack.push(new int[] { i, 1 });
            }

            if (stack.peek()[1] == k)
                stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            int[] cur = stack.pop();
            for (int i = 0; i < cur[1]; i++) {
                sb.append(s.charAt(cur[0]));
            }
        }
        return sb.reverse().toString();
    }
}