package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        char prevOperator = '+';

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (Character.isDigit(cur)) {
                sum = sum * 10 + (cur - '0');
            }

            // 当看到新的operator 或者是最后一个数字的时候，就要进行计算
            if (isOperator(cur) || i == s.length() - 1) {
                if (prevOperator == '+') {
                    stack.push(sum);
                } else if (prevOperator == '-') {
                    stack.push(-1 * sum);
                } else if (prevOperator == '*') {
                    stack.push(stack.pop() * sum);
                } else if (prevOperator == '/') {
                    stack.push(stack.pop() / sum);
                }

                prevOperator = cur;
                sum = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    private boolean isOperator(char cur) {
        return cur == '+' || cur == '-' || cur == '*' || cur == '/';
    }
}