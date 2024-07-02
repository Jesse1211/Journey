// You are given an array of integers numbers and a positive integer k. 
// Your task is to count the number of contiguous subarrays within numbers that contain at least k pairs of elements with duplicate values.

// For numbers = [0, 1, 0, 1, 0] and k = 2, the output should be solution(numbers, k) = 3.
// There are 3 subarrays that satisfy the criteria of containing at least k=2 pairs of duplicate values:

// numbers[0..3] = [0, 1, 0, 1] with numbers[0] = 0 = numbers[2] and numbers[1] = 1 = numbers[3].
// numbers[1..4] = [1, 0, 1, 0] with numbers[1] = 1 = numbers[3] and numbers[2] = 0 = numbers[4].
// numbers[0..4] = [0, 1, 0, 1, 0] with numbers[0] = 0 = numbers[2] and numbers[1] = 1 = numbers[3]; or numbers[2] = 0 = numbers[4] and numbers[1] = 1 = numbers[3]; or numbers[0] = 0 = numbers[4] and numbers[1] = 1 = numbers[3]. Note that numbers[0] = 0 = numbers[2] and numbers[2] = 0 = numbers[4] is not a valid pairing, since each element can be in one pair only.

// For numbers = [2, 2, 2, 2, 2, 2] and k = 3, the output should be solution(numbers, k) = 1.
// There is only 1 applicable subarray numbers[0..5] = [2, 2, 2, 2, 2, 2], which contains at least three pairs of 2s.

// For numbers = [1, 3, 3, 1] and k = 1, the output should be solution(numbers, k) = 4.
// There are 4 subarrays that satisfy the criteria of containing at least k=1 pair of duplicate values:

// numbers[0..2] = [1, 3, 3]
// numbers[0..3] = [1, 3, 3, 1]
// numbers[1..2] = [3, 3]
// numbers[1..3] = [3, 3, 1]
// In each of these subarrays, there is at least a pair of 3s.

import java.util.HashMap;
import java.util.Map;

class Solution {
    // public int kPairsDuplicateValues(int[] numbers, int k) {
    // int res = 0;
    // for (int i = 0; i < numbers.length; i++) {
    // int[] count = new int[101];
    // for (int j = i; j < numbers.length; j++) {
    // count[numbers[j]]++;
    // int pairs = 0;
    // for (int c : count) {
    // pairs += c / 2;
    // }
    // if (pairs >= k) {
    // res++;
    // }
    // }
    // }
    // return res;
    // }

    public int kPairsDuplicateValues(int[] numbers, int k) {
        int res = 0;
        for (int start = 0; start < numbers.length; start++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            int pairs = 0;
            for (int end = start; end < numbers.length; end++) {
                countMap.put(numbers[end], countMap.getOrDefault(numbers[end], 0) + 1);
                if (countMap.get(numbers[end]) % 2 == 0) {
                    pairs++;
                }
                if (pairs >= k) {
                    res++;
                }
            }
        }
        return res;
    }

    public void main(String[] args) {
        int[] numbers = { 0, 1, 0, 1, 0 };
        int k = 2;
        System.out.println(kPairsDuplicateValues(numbers, k));

        int[] numbers1 = { 2, 2, 2, 2, 2, 2 };
        int k1 = 3;
        System.out.println(kPairsDuplicateValues(numbers1, k1));

        int[] numbers2 = { 1, 3, 3, 1 };
        int k2 = 1;
        System.out.println(kPairsDuplicateValues(numbers2, k2));
    }
}