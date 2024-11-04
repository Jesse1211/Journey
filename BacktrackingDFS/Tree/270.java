package BacktrackingDFS.Tree;

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

/// 可以用inorder
class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            int val = root.val;
            if (Math.abs(val - target) < Math.abs(res - target)) {
                // val is closer
                res = val;
            } else if (Math.abs(val - target) == Math.abs(res - target)) {
                if (val < res) {
                    // same close but val is smaller
                    res = val;
                }
            }

            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }

        }
        return res;
    }
}
