package PrefixSum;

class Solution {
    public int maximumSwap(int num) {
        int[] dict = new int[10];
        char[] arr = String.valueOf(num).toCharArray();

        for (int i = 0; i < arr.length; i++) {
            // 这个数字的最后一个index, 左边的没必要移动
            dict[arr[i] - '0'] = i;
        }

        for (int i = 0; i < arr.length; i++) { // 从左往右, 把影响小的换成影响大的
            int currNum = arr[i] - '0';
            for (int k = 9; k > currNum; k--) { // 从右往左, 从大的数字开始找替换
                if (dict[k] > i) {
                    // 数字比curNum大 而且靠右, 交换就是最佳
                    char temp = arr[i];
                    arr[i] = arr[dict[k]];
                    arr[dict[k]] = temp;
                    return Integer.parseInt(new String(arr));
                }
            }
        }

        return num;
    }
}