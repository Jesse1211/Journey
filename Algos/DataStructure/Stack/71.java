package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public String simplifyPath(String path) {
        String[] newPath = path.split("/");
        String res = "";
        Stack<String> stack = new Stack<>();

        for (String s : newPath) {
            if (s.equals(".") || (s.length() == 0)) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }

        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }

        return res.length() == 0 ? "/" : res;
    }
}