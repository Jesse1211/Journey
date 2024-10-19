package PrefixSum;

import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int count = 0;
    int k;
    HashMap<Long, Integer> h = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        k = targetSum;
        preorder(root, 0L);
        return count;
    }

    public void preorder(TreeNode node, long currSum) {
        if (node == null)
            return;

        currSum += node.val;

        if (currSum == k)
            count++;

        count += h.getOrDefault(currSum - k, 0);

        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        preorder(node.left, currSum);
        preorder(node.right, currSum);

        h.put(currSum, h.get(currSum) - 1);
    }
}