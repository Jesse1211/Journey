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
    String[] phoneMap = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.equals("")) {
            return res;
        }
        dfs(digits, res, "", 0);
        return res;
    }

    private void dfs(String digits, List<String> res, String cur, int index) {

        if (index == digits.length()) {
            res.add(cur);
            return;
        }

        int digit = digits.charAt(index) - '0' - 2;

        for (char c : phoneMap[digit].toCharArray()) {
            dfs(digits, res, cur + c, index + 1);
        }
    }
}