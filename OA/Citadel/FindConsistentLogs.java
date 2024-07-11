package OA.Citadel;

import java.util.List;

/*
 * The developers working on a social media network app want to analyze user behavior. 
 * There are n event logs where userEvent[i] denotes the userId for the user that triggered the i-th event. 
 * The team wants to analyze the subarrays of the logs which are consistent, 
 * meaning that the frequency of the most frequent user in the subarray 
 * is equal to the frequency of the least frequent user in the whole array. 
 * Your task is to find the maximum length of such consistent logs.
 * 
 * Example
 * Given n = 10, and userEvent = [1, 2, 1, 3, 4, 2, 4, 3, 3, 4]:
 * The frequencies of 1 and 2 are 2.
 * The frequencies of 3 and 4 are 3.
 * The minimum frequency in the array is 2.
 * The longest valid subarray has 8 elements: [1, 2, 1, 3, 4, 2, 4, 3]
 * The frequencies of 1, 2, 3, and 4 in this subarray are all 2.
 * Hence, the maximum length of consistent logs is 8.
 */
class FindConsistentLogs {
    public static int findConsistentLogs(List<Integer> userEvent) {
        // map + sliding window
        int res = 0;
        return res;
    }
}
