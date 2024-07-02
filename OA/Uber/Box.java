// You are given operations, an array containing the following two types of operations:

// [0, a, b] - Create and save a rectangle of size a × b.
// [1, a, b] - Answer the question: "Could a box of size a × b fit inside each of the earlier saved rectangles?" It is possible to rotate the rectangles by 90 degrees; i.e., a rectangle of dimensions a × b can be rotated so that its dimensions are b × a.
// Your task is to return an array of booleans, representing the answers to the second type of operation, in the order they appear.

// Note: The operations should proceed iteratively, so when operations[i] is executed, only the results of the previous operations 0, 1, ..., i-1 are available.

// Example
// For operations = [[1, 1, 1]], the output should be solution(operations) = [true].
// There are no rectangles, so return true because there were no rectangles that the box was unable to fit inside.

// For operations = [[0, 100000, 100000]], the output should be solution(operations) = [].
// The rectangle was successfully created. Since this type of operation returns nothing and there are no other operations, the answer is an empty array.

// For operations = [[0, 3, 3], [0, 5, 2], [1, 3, 2], [1, 2, 4]], the output should be solution(operations) = [true, false].

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> solution(int[][] operations) {
        List<int[]> rectangles = new ArrayList<>();
        List<Boolean> results = new ArrayList<>();

        for (int[] operation : operations) {
            if (operation[0] == 0) {
                // Create and save a rectangle
                rectangles.add(new int[] { operation[1], operation[2] });
            } else if (operation[0] == 1) {
                // Check if the rectangle can fit
                int a = operation[1];
                int b = operation[2];
                boolean canFit = true;

                for (int[] rect : rectangles) {
                    int rectA = rect[0];
                    int rectB = rect[1];
                    if (!((a <= rectA && b <= rectB) || (a <= rectB && b <= rectA))) {
                        canFit = false;
                        break;
                    }
                }

                results.add(canFit);
            }
        }
        return results;
    }

    // 我感觉不对，因为没有handle面积一样, 形状不一样的情况
    // public List<Boolean> solution(int[][] operations) {
    //     int minA = Integer.MAX_VALUE;
    //     int minB = Integer.MAX_VALUE;
    //     List<Boolean> results = new ArrayList<>();

    //     for (int[] operation : operations) {
    //         if (operation[0] == 0) {
    //             // Create and save a rectangle
    //             int a = operation[1];
    //             int b = operation[2];
    //             // Update the minimum containing rectangle
    //             minA = Math.min(minA, Math.min(a, b));
    //             minB = Math.min(minB, Math.max(a, b));
    //         } else if (operation[0] == 1) {
    //             // Check if the box can fit in the minimum rectangle
    //             int a = operation[1];
    //             int b = operation[2];
    //             boolean canFit = (a <= minA && b <= minB) || (a <= minB && b <= minA);
    //             results.add(canFit);
    //         }
    //     }
    //     return results;
    // }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] operations1 = { { 1, 1, 1 } };
        System.out.println(s.solution(operations1)); // [true]

        int[][] operations2 = { { 0, 100000, 100000 } };
        System.out.println(s.solution(operations2)); // []

        int[][] operations3 = { { 0, 3, 3 }, { 0, 5, 2 }, { 1, 3, 2 }, { 1, 2, 4 } };
        System.out.println(s.solution(operations3)); // [true, false]
    }
}
