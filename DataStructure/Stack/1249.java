
import java.util.Stack;
// meta

class Solution {
    public String minRemoveToMakeValid(String s) {
        int leftParen = 0;
        int rightParen = 0;
        Stack<Character> stack = new Stack<>();

        // eliminate extra ')'
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);
            if (cur == '(') {
                leftParen++;
            } else if (cur == ')') {
                rightParen++;
            }

            if (leftParen < rightParen) { // ')' is more than '('
                rightParen--;
            } else {
                stack.add(cur);
            }
        }

        StringBuilder sb = new StringBuilder();
        // eliminate extra '('
        while (!stack.isEmpty()) {
            Character cur = stack.pop();
            if (leftParen > rightParen && cur == '(') {
                leftParen--;
                continue;
            }
            sb.append(cur);
        }
        return sb.reverse().toString();
    }
}