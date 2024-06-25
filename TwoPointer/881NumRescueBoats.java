package TwoPointer;

import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0, slow = 0, fast = people.length - 1;
        while (slow <= fast) {
            if (people[slow] + people[fast] <= limit) {
                slow++;
            }
            fast--;
            res++;
        }
        return res;
    }
}