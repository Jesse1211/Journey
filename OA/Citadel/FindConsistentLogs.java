package OA.Citadel;

import java.util.*;

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
        // Step 1: Find the minimum frequency in the whole array
        Map<Integer, Integer> totalFrequency = new HashMap<>();
        for (int userId : userEvent) {
            totalFrequency.put(userId, totalFrequency.getOrDefault(userId, 0) + 1);
        }
        int minFrequency = Collections.min(totalFrequency.values());

        // Step 2: Use sliding window to find the maximum length of consistent logs
        Map<Integer, Integer> windowFrequency = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < userEvent.size(); right++) {
            int userId = userEvent.get(right);
            windowFrequency.put(userId, windowFrequency.getOrDefault(userId, 0) + 1);

            // Maintain the window condition: the maximum frequency in the window should be
            // equal to minFrequency
            while (Collections.max(windowFrequency.values()) > minFrequency) {
                int leftUserId = userEvent.get(left);
                windowFrequency.put(leftUserId, windowFrequency.get(leftUserId) - 1);
                if (windowFrequency.get(leftUserId) == 0) {
                    windowFrequency.remove(leftUserId);
                }
                left++;
            }

            // Calculate the length of the current valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        List<Integer> userEvent = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 4, 2, 4, 3, 3, 4));
        System.out.println(findConsistentLogs(userEvent));
    }
}
