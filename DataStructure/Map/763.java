package DataStructure.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * highlight: 使用map的好机会
 */

class Solution {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++)
            map.put(s.charAt(i), i);

        int i = 0;
        while (i < n) {
            int end = map.get(s.charAt(i));
            int start = i + 1;

            while (start < end) {
                int cur = map.get(s.charAt(start));
                if (cur > end) {
                    end = cur;
                }
                start++;
            }
            res.add(end - i + 1);
            i = end + 1;
        }
        return res;
    }
}