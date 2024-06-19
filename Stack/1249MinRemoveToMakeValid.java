package Stack;

import java.util.Stack;

class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s.length() == 0) {
            return s;
        }
        int left = 0, right = 0;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);
            if (cur == '(') {
                left++;
            }
            if (cur == ')') {
                right++;
            }
            if (left < right) {
                right--;
            } else {
                stack.push(cur);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            char cur = stack.pop();
            if (left > right && cur == '(') {
                left--;
            } else {
                res.append(cur);
            }
        }
        return res.reverse().toString();

    }
}