package DataStructure.Heap;

import java.util.PriorityQueue;

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
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        if (root == null) {
            return -1;
        }

        helper(root, pq, k);
        return pq.poll();
    }

    private void helper(TreeNode root, PriorityQueue<Integer> pq, int k) {
        if (root == null) {
            return;
        }
        pq.offer(root.val);
        if (pq.size() > k) {
            pq.poll();
        }
        helper(root.left, pq, k);
        helper(root.right, pq, k);
    }
}