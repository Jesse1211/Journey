package 反向;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[] { left + 1, right + 1 };
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            }
        }
        return new int[] { -1, -1 };
    }
}
// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         int n = numbers.length;
//         for(int i=0;i<n-1;i++){
//            int pos = Arrays.binarySearch(numbers,i+1,n,target-numbers[i]);
//            if(pos>0) return new int[]{i+1,pos+1};
//         }
//         return null;
//     }
// }