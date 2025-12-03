import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 朴实无华的sort
 */

class Solution {
    public String largestNumber(int[] nums) {
        List<String> sList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            sList.add(String.valueOf(nums[i]));
        }

        Collections.sort(sList, (a, b) -> (b + a).compareTo(a + b));

        if (sList.getFirst().equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (var str : sList) {
            sb.append(str);
        }
        return sb.toString();
    }
}