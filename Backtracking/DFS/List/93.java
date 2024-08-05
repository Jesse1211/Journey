package List;

import java.util.ArrayList;
import java.util.List;

/* highlight: bfs内部的forloop不仅是遍历所有children, 还有当前level, 比如这里的segment
 * 1. 判定dfs的parameters: s, index, segNum, res, cur
 *      当前char取决于index
 *      当前segment包含的char取决于index 和 i
 *      每个segment更新一次cur, 最终加到res中
 *      用segNum来判定是否有三个segments
 * 2. dfs 何时停止: s.length() == index && segNum == 4 合理并且有四个segments
 * 3. dfs 如何更新: cur + s.substring(index, index + i)
 */

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();

        if (s.length() > 12) {
            return res;
        }

        dfs(s, 0, 0, res, "");
        return res;
    }

    private void dfs(String s, int index, int segNum, List<String> res, String cur) {
        if (s.length() == index && segNum == 4) {
            res.add(cur);
            return;
        }

        // current segment
        for (int i = 1; i <= 3; i++) {

            if (index + i > s.length()) {
                break;
            }

            String curVal = s.substring(index, index + i);
            if ((curVal.length() > 1 && curVal.charAt(0) == '0') || Integer.parseInt(curVal) >= 256) {
                break;
            }

            String temp = cur;

            if (index == 0) {
                cur += curVal;
            } else {
                cur += ".";
                cur += curVal;
            }

            dfs(s, index + i, segNum + 1, res, cur);
            cur = temp;
        }
    }
}