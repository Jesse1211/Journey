package 两个array;

import java.util.ArrayList;
import java.util.List;

class SparseVector {
    List<int[]> indexToValue;

    SparseVector(int[] nums) {
        this.indexToValue = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                this.indexToValue.add(new int[] { i, nums[i] });
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int left = 0;
        int right = 0;
        int res = 0;
        while (left < this.indexToValue.size() && right < vec.indexToValue.size()) {
            int[] leftValue = this.indexToValue.get(left);
            int[] rightValue = vec.indexToValue.get(right);

            if (leftValue[0] < rightValue[0]) {
                left++;
            } else if (leftValue[0] > rightValue[0]) {
                right++;
            } else {
                left++;
                right++;
                res += leftValue[1] * rightValue[1];
            }
        }
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);