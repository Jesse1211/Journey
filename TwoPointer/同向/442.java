package 同向;

class Solution {
    public int compress(char[] chars) {
        int index = 0;
        int slow = 0;

        while (index < chars.length) {

            int oldIndex = index;
            while (index + 1 < chars.length && chars[index] == chars[index + 1]) {
                index++;
            }

            chars[slow] = chars[oldIndex];
            slow++;
            if (oldIndex != index) {
                String distance = (index - oldIndex + 1) + "";
                for (char c : distance.toCharArray()) {
                    chars[slow] = c;
                    slow++;
                }
            }
            index++;
        }

        return slow;
    }
}