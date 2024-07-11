package OA.Citadel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// There are n users indexed from 0 to n-1,m friends
// and a 2d array: friendships, where the ith friendship is a connection between users friendship[i][0] and friendship[i][1].

// User x is suggested as a friend to user y if x and y are not friends and have the maximum number of common friends, 
// i.e. a friend of both x and y. 
// If there are multiple possible such users x, the one with the minimum index is suggested.

// Given n and friendships, for each of the n users, find the index of the friend that should be recommended to them. If there is no recommendation available, report -1.

// Example
// Suppose n = 5, m = 5, and connections = [[0, 1], [0, 2], [1, 3], [2, 3], [3, 4]]
// Hence the answer returned is [3, 2, 1, 0, 1].

class Solution {
    public List<Integer> getRecommenationFriends(int n, int[][] friendship) {
        Map<Integer, Set<Integer>> friends = new HashMap<>();
        for (int i = 0; i < n; i++) {
            friends.put(i, new HashSet<>());
        }

        for (int[] f : friendship) {
            friends.get(f[0]).add(f[1]);
            friends.get(f[1]).add(f[0]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (i == j || friends.get(i).contains(j)) {
                    continue;
                }

                for (int friend : friends.get(i)) {
                    if (friends.get(j).contains(friend)) {
                        cur = Math.min(cur, friend);
                    }
                }
            }

            if (cur == Integer.MAX_VALUE) {
                res.add(-1);
            } else {
                res.add(cur);
            }
        }

        return res;
    }
}