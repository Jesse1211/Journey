package 反向;

/*
 * highlight: 用不一样的incrementation分批做two pointer
 */

 class Solution {
    public String reverseStr(String s, int k) {
        int slow = 0;
        int fast = 2 * k - 1;
        int mid = k - 1;
        String cur = "";
        while (fast < s.length()) {
            // reverse left half, don't change right half
            cur += reverse(s, slow, mid);
            cur += s.substring(mid+1, fast+1);
            // update
            slow = fast + 1;
            fast += 2 * k;
            mid = slow + k;
        }
        // cur += reverse(s, slow, mid);
        cur += s.substring(mid+1, s.length());
        return cur;
    }

    private String reverse(String s, int slow, int fast) {
        StringBuilder sb = new StringBuilder();
        while (slow < fast) {
            sb.append(s.charAt(fast--));
            sb.append(s.charAt(slow++));
        }
        return sb.toString();
    }
}
