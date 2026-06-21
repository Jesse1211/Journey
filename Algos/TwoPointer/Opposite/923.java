package Opposite;

import java.util.Arrays;

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        long res =0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int comp = target - arr[i];
            int j = i+1;
            int k = arr.length - 1;

            while (j < k) {
                if (arr[j] + arr[k] < comp) {
                    j++;
                } else if (arr[j] + arr[k] > comp) {
                    k--;
                } else {
                    int leftDup = 1;
                    int rightDup = 1;

                    if (arr[j] == arr[k]) {
                        res += (k - j + 1) * (k - j) / 2;
                        break;
                    }

                    while (j < k && arr[j] == arr[j+1]) {
                        leftDup ++;
                        j++;
                    }
                    while (j < k && arr[k-1] == arr[k]) {
                        rightDup++;
                        k--;
                    }

                    res += leftDup * rightDup;
                    j++;
                    k--;
                }

            }

        }
        return (int) (res % 1000000007);
    }
}