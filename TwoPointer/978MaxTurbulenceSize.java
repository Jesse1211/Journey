package TwoPointer;

class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int res = 1;
        int start = 0, end = 0;
        while (start + 1 < arr.length) {
            if (arr[start] == arr[start + 1]) {
                start++;
            } else {
                end = start + 1;
                while (end < arr.length - 1 && isTurbulent(arr, end)) {
                    end++;
                }
                res = Math.max(res, (end - start + 1));
                start = end;
            }

        }
        return res;
    }

    private boolean isTurbulent(int arr[], int cur) {
        return (arr[cur] < arr[cur + 1] && arr[cur] < arr[cur - 1])
                || (arr[cur] > arr[cur + 1] && arr[cur] > arr[cur - 1]);
    }
}