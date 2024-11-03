package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.List;

/*
 * 1. 判定dfs的parameters: digits, index, res, cur
*      当前candidates取决于digits & index
*      层层更新cur, 最终加到res中
 * 2. dfs 何时停止: 没有candidates, 所有的digits都被处理
 * 3. dfs 如何更新: cur + letter, 
*      letter是当前digits的一个candidate
*      用forloop来遍历所有的candidates
 */
class Solution {
    String[] phoneMap = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.equals("")) {
            return res;
        }
        dfs(digits, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(String digits, int index, StringBuilder cur, List<String> res) {
        if (index == digits.length()) {
            res.add(cur.toString());
            return;
        }

        int curDigit = digits.charAt(index) - '0';
        for (int i = 0; i < phoneMap[curDigit].length(); i++) {
            cur.append(phoneMap[curDigit].charAt(i));
            dfs(digits, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}