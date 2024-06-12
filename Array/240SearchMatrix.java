package Array;
// matrix里面找中间值, 这个case里面是右上角
// 如果matrix[i][j] == target, return true
// 如果matrix[i][j] < target, i++ 下一行
// 如果matrix[i][j] > target, j-- 左一列
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
		// Start from the top-right corner
        int i=0;
        int j=matrix[0].length-1;
        while(j>=0 && i<matrix.length){
            if(matrix[i][j]==target)return true;
            if(j-1>=0 && matrix[i][j-1]<target){
                i++;
            }
            else{
                if(j-1<0)i++;
                else j--;
            }
        }
        return false;
    }
}