
class Solution {
    public int longestMountain(int[] arr) {
        int res = 0;
        int i = 0;
        while (i < arr.length) {
            int leftBound = i;

            if (i < arr.length - 1 && arr[i] < arr[i + 1]) {
                // if there is peak at right
                while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
                    i++;
                }

                // if there is slope at right
                while (i < arr.length - 1 && arr[i] > arr[i + 1]) {
                    i++;
                    res = Math.max(res, i - leftBound + 1); // 上坡 + 下坡才算
                }
            }

            if (i == leftBound) {
                i++;
            }
        }

        return res;

    }
}