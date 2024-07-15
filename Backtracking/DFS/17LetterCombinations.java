import java.util.ArrayList;
import java.util.List;

class Solution {
    String[] phoneMap = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        if (digits.isEmpty())
            return res;

        dfs(digits, 0, res, "");
        return res;
    }

    private void dfs(String digits, int index, List<String> res, String cur) {

        if (index == digits.length()) {
            res.add(cur);
            return;
        }

        String letters = phoneMap[digits.charAt(index) - '2'];
        for (char letter : letters.toCharArray()) {
            dfs(digits, index + 1, res, cur + letter);
        }
    }
}
