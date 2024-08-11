package TwoPointer;

class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int[] visited = new int[26];
        int left = 0, res = 0, freq = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            visited[arr[i] - 'A'] += 1;
            freq = Math.max(freq, visited[arr[i] - 'A']);
            if (i - left + 1 - freq > k) {
                visited[arr[left] - 'A'] -= 1;
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }
}