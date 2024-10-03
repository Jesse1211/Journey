import java.util.Stack;

// meta
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        Character operator = '+';
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);

            // cur is a number / a prefix of a number
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }

            if (isOperator(cur) || i == s.length() - 1) {
                if (operator == '+') {
                    stack.push(+num);
                } else if (operator == '-') {
                    stack.push(-num);
                } else if (operator == '*') {
                    stack.push(stack.pop() * num);
                } else if (operator == '/') {
                    stack.push(stack.pop() / num);
                }
                operator = cur;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    private boolean isOperator(Character c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}