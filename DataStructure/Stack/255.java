package DataStructure.Stack;

import java.util.Stack;

class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> s = new Stack<>();

        // min在这里做为当前node的最小boundary, 因为是preorder, 我们不需要考虑最大boundary
        int min = Integer.MIN_VALUE;

        for (int cur : preorder) {
            if (cur < min) {
                return false;
            }

            while (!s.isEmpty() && s.peek() < cur) {
                // 保留cur的parent - min
                min = s.pop();
            }

            s.push(cur);
        }

        return true;
    }
}