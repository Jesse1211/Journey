
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String child = countAndSay(n - 1);

        StringBuilder sb = new StringBuilder();
        int count = 0;
        int i = 0;
        while (i < child.length()) {
            char cur = child.charAt(i);
            while (i < child.length() && child.charAt(i) == cur) {
                i++;
                count++;
            }
            sb.append(count).append(cur);
            count = 0;
        }

        return sb.toString();
    }
}