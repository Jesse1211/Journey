// Given an array of strings words, find the number of pairs where either the
// strings are equal or one string starts with another. In other words, find the
// number of such pairs (i, j) (where 0 ≤ i < j < words.length) that words[i] is
// a prefix of words[j] or words[j] is a prefix of words[i].

// Example

// For words ["back", "backdoor", "gammon", "backgammon", "comeback", "come",
// "door"], the output should be solution(words) = 3.
// The relevant pairs are:

// words[0] = "back" and words[1] = "backdoor"

// words[0] = "back" and words[3] = "backgammon"

// words[4] = "comeback" and words[5] = "come"

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int wordPairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int count = 0;
        String[] uniqueWords = map.keySet().toArray(new String[0]); // Get all unique words from the map

        // Count pairs where one word is a prefix of another
        for (int i = 0; i < uniqueWords.length; i++) {
            for (int j = i + 1; j < uniqueWords.length; j++) {
                if (isPrefix(uniqueWords[i], uniqueWords[j])) {
                    count += map.get(uniqueWords[i]) * map.get(uniqueWords[j]);
                }
            }
            // Add pairs where the same word is a prefix of itself and appears more than
            // once
            if (map.get(uniqueWords[i]) > 1) {
                count += map.get(uniqueWords[i]) * (map.get(uniqueWords[i]) - 1) / 2;
            }
        }

        return count;
    }

    public int wordPairsBinarySearch(String[] words) {
        Arrays.sort(words); // Sort the array
        int totalPairs = 0;

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            int start = i + 1; // Start looking for prefixes after the current word
            int end = words.length - 1;
            int count = 0;

            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (words[mid].startsWith(currentWord)) {
                    if (mid == start || !words[mid - 1].startsWith(currentWord)) {
                        // Found the first index where prefix starts
                        for (int j = mid; j < words.length && words[j].startsWith(currentWord); j++) {
                            count++;
                        }
                        break;
                    } else {
                        end = mid - 1;
                    }
                } else if (words[mid].compareTo(currentWord) > 0) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            totalPairs += count;
        }
        return totalPairs;
    }

    private boolean isPrefix(String a, String b) {
        return a.startsWith(b) || b.startsWith(a);
    }

}