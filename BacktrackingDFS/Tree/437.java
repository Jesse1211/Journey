package BacktrackingDFS.Tree;

import java.util.HashMap;
import java.util.Map;

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

class Solution1 {
    int count = 0;
    int targetSum;
    Map<Long, Integer> h = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        preorder(root, 0L);
        return count;
    }

    public void preorder(TreeNode node, long currSum) {
        if (node == null)
            return;

        currSum += node.val;

        if (currSum == targetSum)
            count++;

        count += h.getOrDefault(currSum - targetSum, 0);
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        preorder(node.left, currSum);
        preorder(node.right, currSum);

        h.put(currSum, h.get(currSum) - 1);
    }

}

class Solution2 {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        return pathSum(root.left, targetSum) +
                pathSum(root.right, targetSum) +
                (int) dfs(root, targetSum);
    }

    private long dfs(TreeNode root, long cur) {
        if (root == null) {
            return 0;
        }

        long res = 0;

        if (cur == root.val) {
            res++;
        }

        return res +
                dfs(root.left, cur - root.val) +
                dfs(root.right, cur - root.val);
    }
}