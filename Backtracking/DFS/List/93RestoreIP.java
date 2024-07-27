package List;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() > 12)
            return res;
        dfs(s, res, new StringBuilder(), 0, 0);
        return res;

    }

    private void dfs(String s, List<String> res, StringBuilder cur, int segNum, int index) {
        if (index == s.length() && segNum == 4) {
            res.add(cur.toString());
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length())
                break;
            String val = s.substring(index, i + index);
            if (val.length() > 1 && val.startsWith("0") || Integer.parseInt(val) >= 256)
                break;

            int l = cur.length(); // Since we have append(".")before
            if (index == 0)
                cur.append(val);
            else {
                cur.append(".");
                cur.append(val);
            }

            dfs(s, res, cur, segNum + 1, index + i);
            cur.delete(l, l + i + 1);

        }
    }
}