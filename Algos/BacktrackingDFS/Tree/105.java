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
    int pIndex;
    int iIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, Integer.MAX_VALUE); // Integer.MAX_VALUE is a place holder for root
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int parentVal) {
        // base case 1: entire tree is done
        if (pIndex == preorder.length) {
            return null;
        }

        // base case 2: sub tree is done
        if (inorder[iIndex] == parentVal) {
            iIndex++;
            return null;
        }

        TreeNode cur = new TreeNode(preorder[pIndex]);
        pIndex++;
        TreeNode left = dfs(preorder, inorder, cur.val);
        TreeNode right = dfs(preorder, inorder, parentVal);

        cur.left = left;
        cur.right = right;
        return cur;
    }
}
class Solution2 {
    Map<Integer, Integer> iMap = new HashMap<>(); // value : index

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            iMap.put(inorder[i], i);
        }

        return dfs(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int pIndex, int iStart, int iEnd) {
        if (pIndex == preorder.length || iStart > iEnd) {
            return null;
        }

        TreeNode cur = new TreeNode(preorder[pIndex]);
        int inorderIndex = iMap.get(preorder[pIndex]);

        int leftP = pIndex + 1;
        cur.left = dfs(preorder, inorder, leftP, iStart, inorderIndex - 1);

        int rightP = pIndex + (inorderIndex - iStart) + 1;
        cur.right = dfs(preorder, inorder, rightP, inorderIndex + 1, iEnd);
        return cur;
    }
}