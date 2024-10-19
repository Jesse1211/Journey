package BFS.FromCenter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<String>();
        for (String word : wordList) {
            wordSet.add(word);
        }

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();

        int res = 1;

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String target = word.substring(0, i) + c + word.substring(i + 1);

                        if (endSet.contains(target)) {
                            return res + 1;
                        }

                        if (wordSet.contains(target)) {
                            temp.add(target);
                            wordSet.remove(target);
                        }
                    }
                }
            }
            beginSet = temp;
            res++;
        }

        return 0;
    }
}