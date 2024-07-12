package OA.Citadel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * The goodness of a sequence is defined as the bitwise-OR of its elements. 
 * Given an array arr of length n, 
 * find all possible distinct values of goodness 
 * that can be obtained by choosing any strictly increasing subsequence of the array. 
 * Sort the return array in non-decreasing order.
 * 
 * Subsequence: A sequence derived from the array by deleting zero or more elements 
 * without changing the order of the remaining elements.
 * 
 * Example
 * Consider n = 4 and arr = [4, 2, 4, 1]:
 * 
 * The strictly increasing subsequences and their goodness values are:
 * Empty subsequence: goodness = 0
 * [1]: goodness = 1
 * [2]: goodness = 2
 * [4]: goodness = 4
 * [2, 4]: goodness = 6
 * So, the distinct goodness values are [0, 1, 2, 4, 6].
 */

class GetDistinctGoodnessValues {
    public static List<Integer> getDistinctGoodnessValues(List<Integer> arr) {
        // Use a set to store distinct goodness values
        Set<Integer> goodnessValues = new HashSet<>();
        // Add the goodness value of the empty subsequence
        goodnessValues.add(0);

        // Find all strictly increasing subsequences
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> current = new ArrayList<>();
            for (int j = i; j < arr.size(); j++) {
                if (current.isEmpty() || arr.get(j) > current.get(current.size() - 1)) {
                    current.add(arr.get(j));
                    // Calculate the goodness value of the current subsequence
                    int goodness = 0;
                    for (int num : current) {
                        goodness |= num;
                    }
                    // Add the goodness value to the set
                    goodnessValues.add(goodness);
                }
            }
        }

        // Convert the set to a list and sort it
        List<Integer> sortedGoodnessValues = new ArrayList<>(goodnessValues);
        Collections.sort(sortedGoodnessValues);

        return sortedGoodnessValues;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(4, 2, 4, 1);
        System.out.println(getDistinctGoodnessValues(arr)); // Output: [0, 1, 2, 4, 6]
    }
}
