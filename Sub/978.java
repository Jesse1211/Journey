package Sub;

class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int res = 1;
        int slow = 0, fast = 0;
        while (slow < arr.length - 1) {
            if (arr[slow] == arr[slow + 1]) {
                slow++;
            } else {
                fast = slow + 1;
                while (fast < arr.length - 1 && isTurbulent(arr, fast)) {
                    fast++;
                }
                res = Math.max(res, (fast - slow + 1));
                slow = fast;
            }

        }
        return res;
    }

    private boolean isTurbulent(int arr[], int cur) {
        return (arr[cur] < arr[cur + 1] && arr[cur] < arr[cur - 1])
                || (arr[cur] > arr[cur + 1] && arr[cur] > arr[cur - 1]);
    }
}