package DP.OA;

class Solution {
    public int findMaxLength(int[] skills, int k) {
        int res = 1;
        int cur = 1;
        for (int i = 0; i < skills.length - 1; i++) {
            if (skills[i] == skills[i + 1]) {
                cur += 1;
                res = Math.max(res, cur);
                continue;
            }
            int newCur = 0;
            for (int j = i; j >= 0; j--) {
                if (skills[i] - skills[j] > k) {
                    continue;
                } else {
                    newCur += 1;
                }
            }
            cur = Math.max(res, newCur);
            res = Math.max(res, cur);
        }
        return res;
    }

}