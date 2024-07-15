import java.util.ArrayList;
import java.util.List;

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