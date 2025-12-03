package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private int minRemoveCount = Integer.MAX_VALUE;
    private Set<String> tempRes = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, 0, 0, 0, new StringBuilder());
        return new ArrayList<String>(this.tempRes);
    }

    private void dfs(String s, int index, int leftCount, int rightCount, int removeCount, StringBuilder cur) {
        if (removeCount > minRemoveCount) {
            return;
        }
        if (index == s.length()) {
            if (leftCount != rightCount) {
                return;
            }

            if (removeCount < minRemoveCount) {
                tempRes.clear();
                minRemoveCount = removeCount;
            }
            tempRes.add(cur.toString());
            return;
        }

        char c = s.charAt(index);
        // if (Character.isLetter(c)) {
        if (c != ')' && c != '(') {
            cur.append(c);
            dfs(s, index + 1, leftCount, rightCount, removeCount, cur);
            cur.deleteCharAt(cur.length() - 1);
            return; // since it's not parentheses, no need to go below
        }

        // option1: skip
        dfs(s, index + 1, leftCount, rightCount, removeCount + 1, cur);

        // option2: take
        cur.append(c);
        if (c == ')' && rightCount < leftCount) {
            dfs(s, index + 1, leftCount, rightCount + 1, removeCount, cur);
        } else if (c == '(') {
            dfs(s, index + 1, leftCount + 1, rightCount, removeCount, cur);
        }
        cur.deleteCharAt(cur.length() - 1);
    }
}