package BacktrackingDFS.List;

class Solution {
    int res = 0;

    public int countArrangement(int n) {
        boolean[] visited = new boolean[n + 1];
        dfs(n, 1, visited);
        return res;
    }

    private void dfs(int n, int curVal, boolean[] visited) {
        if (curVal > n) {
            res++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == true) {
                continue;
            }

            if (i % curVal == 0 || curVal % i == 0) {
                visited[i] = true;
                dfs(n, curVal + 1, visited);
                visited[i] = false;
            }
        }
    }
}