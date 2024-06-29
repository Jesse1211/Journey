package OA.Ramp;

class Solution {

    public String solution(String src) {
        String[] strs = src.split(" ");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].indexOf("_") != -1) {
                strs[i] = convert(strs[i]);
            }
        }
        return String.join(" ", strs);
    }

    public String convert(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int dis = 'A' - 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '_') {
                int left = i - 1;
                int right = i + 1;
                if (left >= 0 && s.charAt(left) != '_' && right < n && s.charAt(right) != '_') {
                    i = right;
                    char upper = (char) (s.charAt(right) - dis);
                    sb.append(upper);
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}