package List;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<List<String>> result;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        dfs(s, new ArrayList<>());
        return result;
    }

    private void dfs(String s, List<String> cur) {
        if (s.length() == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            String prefix = s.substring(0, i + 1);
            if (isPalindrome(prefix)) {
                cur.add(prefix);
                dfs(s.substring(i + 1), cur);
                cur.remove(cur.size() - 1);
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