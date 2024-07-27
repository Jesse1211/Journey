package List;

import java.util.HashSet;

class Solution {
    public int numTilePossibilities(String tiles) {
        HashSet<String> set = new HashSet<>();
        boolean[] visited = new boolean[tiles.length()];

        dfs(set, "", tiles, visited);
        return set.size() - 1;
    }

    public void dfs(HashSet<String> set, String cur, String tiles, boolean[] visited) {
        set.add(cur);
        for (int i = 0; i < tiles.length(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            set.add(cur);
            dfs(set, cur + tiles.charAt(i), tiles, visited);
            visited[i] = false;
        }
    }
}