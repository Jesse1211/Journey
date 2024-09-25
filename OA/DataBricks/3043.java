package OA.DataBricks;

import java.util.HashMap;

/*
 * highlight: longest common prefix
 */

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // store all digits' frequency in arr1
        for (int i = 0; i < arr1.length; i++) {
            int temp = arr1[i];
            while (temp > 0) {
                map.put(temp, map.getOrDefault(temp, 0) + 1);
                temp /= 10;
            }
        }
        int ans = 0;
        for (int i = 0; i < arr2.length; i++) {
            int temp = arr2[i];
            // check all digits in arr2 if in arr1
            while (temp > 0) {
                if (map.containsKey(temp)) {
                    // update the length of the common prefix
                    // 找到右边一个element和左边所有element的最长公共前缀 - 好好审题!
                    if (Integer.toString(temp).length() > ans) {
                        ans = Integer.toString(temp).length();
                    }
                }
                temp /= 10;
            }
        }
        return ans;
    }
}