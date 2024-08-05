package Tree;

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

/*
 * highlight: 基本功要会
 * 1. 递归的思想
 *      递归的终止条件:
 *          preorder遍历完说明树已经构建完毕
 *          inorder遍历到stop说明子树构建完毕
 * 递归的过程:
 *      1. 构建根节点
 *      2. 构建左子树
 *      3. 构建右子树
 * 2. 递归的参数:
 *      preorder: 前序遍历数组
 *      inorder: 中序遍历数组
 *      stop: 中序遍历的终止条件
 * 3. 递归的返回值:
 *      构建好的树
 */

class Solution {
    private int i = 0;
    private int p = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (p >= preorder.length) { // tree complete
            return null;
        }
        if (inorder[i] == stop) { // sub tree complete
            ++i;
            return null;
        }

        TreeNode node = new TreeNode(preorder[p++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }
}