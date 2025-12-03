package BacktrackingDFS.List;

import java.util.ArrayList;
import java.util.List;

/* highlight: bfs内部的forloop不仅是遍历所有children, 还有当前level, 比如这里的segment
 * 1. 判定dfs的parameters: s, index, segNum, res, cur
 *      当前char取决于index
 *      当前segment包含的char (从1到3个)取决于index 和 i
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
        dfs(res, s, 0, 0, "");
        return res;
    }

    private void dfs(List<String> res, String s, int segNum, int index, String cur) {
        if (segNum == 4 && index == s.length()) {
            res.add(cur);
            return;
        }

        // choose 1 to 3 integers for current segment
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length()) {
                break;
            }

            String curSeg = s.substring(index, index + i);
            if (Integer.parseInt(curSeg) >= 256 || (curSeg.length() > 1 && curSeg.charAt(0) == '0')) {
                break;
            }

            String tmp = cur;

            // segment start from DOT if not first segment
            if (index != 0) {
                cur += '.';
            }
            cur += curSeg;
            dfs(res, s, segNum + 1, index + i, cur);
            cur = tmp;
        }

    }
}