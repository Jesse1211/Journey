package BacktrackingDFS.List;

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode str2tree(String s) {
        if (s.length() == 0)
            return null;

        Stack<TreeNode> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ')') {
                stack.pop();
            } else if (c == '(') {
                continue;
            } else {

                int slow = i;
                while (i + 1 < s.length() && s.charAt(i + 1) != ')' && s.charAt(i + 1) != '(') {
                    // 和+1的比较, 为了之后方便做subtring
                    i++;
                }

                int cur = Integer.parseInt(s.substring(slow, i + 1));
                TreeNode newNode = new TreeNode(cur);
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();

                    if (parent.left != null) {
                        parent.right = newNode;
                    } else {
                        parent.left = newNode;
                    }

                }
                stack.push(newNode);
            }
        }
        return stack.peek();
    }
}