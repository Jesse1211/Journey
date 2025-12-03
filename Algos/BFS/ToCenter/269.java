package BFS.ToCenter;

import java.util.*;

class Solution {
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjList = new HashMap<>(); // c : [chars greater than c]
        Map<Character, Integer> counts = new HashMap<>(); // c : nums of char smaller than c

        for (String w : words) {
            for (char c : w.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            // check if valid
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new ArrayDeque<>();
        for (var entry : counts.keySet()) {
            if (counts.get(entry) == 0) {
                q.offer(entry);
                sb.append(entry);
            }
        }

        while (!q.isEmpty()) {
            char cur = q.poll();
            for (char next : adjList.get(cur)) {
                counts.put(next, counts.get(next) - 1);

                if (counts.get(next) == 0) {
                    q.offer(next);
                    sb.append(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }
}