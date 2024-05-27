package OA;

// https://www.fastprep.io/problems/snowflake-array-reduction-algorithm
class Solution {

    public int[] arrayReductionAlgorithm(int[] arr) {
        int[] result = new int[arr.length];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (compareArrays(findMEX(arr, i), result) == 0) {
                result[0] = arr[i];
            }
            // if result, findMEX(result, i));
        }
        // write your code here
    }

    private int compareArrays(int[] x, int[] y) {
        int minLength = Math.min(x.length, y.length);
        for (int i = 0; i < minLength; i++) {
            if (x[i] != y[i]) {
                return Integer.compare(x[i], y[i]);
            }
        }
        return Integer.compare(x.length, y.length);
    }

    private int[] findMEX(int[] arr, int k) {
        int[] list = new int[k];
        int index = 0;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            if (index >= k) {
                break;
            }
            if (arr[i] != cur) {
                list[index] = arr[i];
            }
            cur++;
        }

        if (index < k) {
            int last = list[index - 1];
            for (int i = index; i < k; i++) {
                list[i] = ++last;
            }
        }
        return list;
    }

}