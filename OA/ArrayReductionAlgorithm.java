package OA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://www.fastprep.io/problems/snowflake-array-reduction-algorithm
class Solution {

    public int[] arrayReductionAlgorithm(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        while (arr.length > 0) {
            int bestMEX = -1;
            int index = 0;
            for (int k = 1; k <= arr.length; k++) {
                int mex = findMEX(arr, k);
                if (mex > bestMEX) {
                    bestMEX = mex;
                    index = k;
                }
            }
            System.out.println(arr.length);
            list.add(bestMEX);
            arr = Arrays.copyOfRange(arr, index, arr.length);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    private int findMEX(int[] array, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            set.add(array[i]);
        }
        int mex = 0;
        while (set.contains(mex)) {
            mex++;
        }
        return mex;
    }
}