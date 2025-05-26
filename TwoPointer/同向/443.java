package 同向;

class Solution {
    public int compress(char[] chars) {
        int slow = 0;
        int fast = 0;

        while (fast < chars.length) {
            int start = fast;

            while (fast < chars.length - 1 && chars[fast] == chars[fast + 1]) {
                fast++;
            }

            chars[slow] = chars[fast];

            int count = fast - start + 1;

            if (count == 1) {
                slow++;
                fast++;
                continue;
            }

            slow++;
            for (char c : Integer.toString(count).toCharArray()) {
                chars[slow] = c;
                slow++;
            }

            fast++;
        }

        return slow;
    }
}