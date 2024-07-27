package List;

// from 1 to n, each time choose only k numbers

import java.util.ArrayList;
import java.util.List;

/* highlight:  如果找的结果是有顺序的, 每次recursive call的时候用的是for里面的i, 而不是index
 * 1. 判定dfs的parameters: n, k, res, cur, index
 *     当前n取决于index
 *    层层更新cur, 最终加到res中
 * 2. dfs 何时停止: k == 0
 * 3. dfs 如何更新: cur + i,
 *    用forloop来遍历所有的n
 */

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, n, k, new ArrayList<>(), 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, int n, int k, List<Integer> cur, int index) {

        if (k == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < n + 1; i++) {
            cur.add(i);
            dfs(res, n, k - 1, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}