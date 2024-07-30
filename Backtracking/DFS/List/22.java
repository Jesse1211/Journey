package List;

import java.util.ArrayList;
import java.util.List;

/*
 * 1. 判定dfs的parameters: n, res, cur, left, right
*      当前candidates取决于left, right
*      层层更新cur, 最终加到res中
 * 2. dfs 何时停止: cur.length() == 2 * n
 * 3. dfs 如何更新: 增加 "(" 或者 ")"
*      “(” 的数量小于n时，可以增加“(”
*      “)” 的数量小于“(”时，可以增加“)”
 */

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        dfs(res, n, "", 0, 0);
        return res;
    }

    private void dfs(List<String> res, int n, String cur, int left, int right) {

        if (cur.length() == 2 * n) {
            res.add(cur);
            return;
        }

        if (left < n) {
            dfs(res, n, cur + "(", left + 1, right);
        }

        if (left > right) {
            dfs(res, n, cur + ")", left, right + 1);
        }

    }
}