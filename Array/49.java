
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 普通的array题要学会借助其他structure来解决问题
 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (var str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = new String(chars);
            if (!map.containsKey(newStr)) {
                map.put(newStr, new ArrayList<>());
            }

            map.get(newStr).add(str);
        }
        return new ArrayList<>(map.values());

    }
}