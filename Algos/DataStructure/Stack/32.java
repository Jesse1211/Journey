package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>(); // index
        stack.push(-1);// stack.peek() returns the index before the matching '('

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i); // push ')' if empty
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}