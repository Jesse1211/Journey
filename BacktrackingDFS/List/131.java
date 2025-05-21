package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.List;

/* 
 * 1. 判定dfs的parameters: res, s, index, cur
 *      当前char取决于index
 *      当前segment包含的char取决于index 和 i
 *      每个segment更新一次cur, 最终加到res中
 * 2. dfs 何时停止: s.length() == index
 * 3. dfs 如何更新: cur + s.substring(index, index + i)
 */
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, s, 0, new ArrayList<>());
        return res;
    }

    private void dfs(List<List<String>> res, String s, int index, List<String> cur) {
        if (s.length() == index) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (isPalindrome(sub)) {
                cur.add(sub);
                dfs(res, s, i + 1, cur);
                cur.removeLast();
            }
        }
    }

    private boolean isPalindrome(String str) {
        int low = 0, high = str.length() - 1;

        while (low < high) {
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}