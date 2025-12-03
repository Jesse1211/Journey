package BacktrackingDFS.List;

import java.util.HashSet;
import java.util.Set;

/*
 * 1. 判定dfs的parameters: tiles, visited, res, cur
 *      visited防重覆使用一个index
 *      res防重覆添加一个string
 *     cur记录当前的string来自于tiles
 * 2. dfs 何时停止: 当前string已经添加到res中, 不需要再看能怎么添加了
 * 3. dfs 如何更新: 
 *      先把当前string加入res
 *      试着给cur加一个新的char: dfs(tiles, visited, res, cur + tiles.charAt(i));
 */

class Solution {
    public int numTilePossibilities(String tiles) {
        Set<Integer> visited = new HashSet<>();
        Set<String> res = new HashSet<>();
        dfs(tiles, visited, res, "");
        return res.size() - 1;
    }

    private void dfs(String tiles, Set<Integer> visited, Set<String> res, String cur) {
        if (res.contains(cur)) {
            return;
        }

        res.add(cur);

        for (int i = 0; i < tiles.length(); i++) {
            if (visited.contains(i)) {
                continue;
            }

            visited.add(i);
            dfs(tiles, visited, res, cur + tiles.charAt(i));
            visited.remove(i);
        }
    }
}