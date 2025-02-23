
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    // 只需要先把最多的分开存, 之后的都无所谓, 这样是O(n)
    public String reorganizeString(String s) {
        var charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }
        int maxCount = 0, letter = 0;
        for (int i = 0; i < charCounts.length; i++) {
            if (charCounts[i] > maxCount) {
                maxCount = charCounts[i];
                letter = i;
            }
        }
        if (maxCount > (s.length() + 1) / 2) {
            return "";
        }
        var ans = new char[s.length()];
        int index = 0;

        // Place the most frequent letter
        while (charCounts[letter] != 0) {
            ans[index] = (char) (letter + 'a');
            index += 2;
            charCounts[letter]--;
        }

        // Place rest of the letters in any order
        for (int i = 0; i < charCounts.length; i++) {
            while (charCounts[i] > 0) {
                if (index >= s.length()) {
                    index = 1;
                }
                ans[index] = (char) (i + 'a');
                index += 2;
                charCounts[i]--;
            }
        }

        return String.valueOf(ans);
    }

    class Pair {
        Character key;
        int freq;

        Pair(Character key, int freq) {
            this.key = key;
            this.freq = freq;
        }
    }

    public String reorganizeString2(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for (var c : map.entrySet()) {
            pq.add(new Pair(c.getKey(), c.getValue()));
        }

        if (pq.peek().freq > (s.length() + 1) / 2) {
            return "";
        }

        int index = 0;
        StringBuilder sb = new StringBuilder(s);

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            // 必须一次把所有的cur.key都放进去
            for (int i = 0; i < cur.freq; i++) {

                sb.setCharAt(index, cur.key);
                index += 2;

                if (index >= s.length()) {
                    index = 1;
                }
            }
        }

        return sb.toString();
    }
}
