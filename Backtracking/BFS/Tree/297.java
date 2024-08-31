package Backtracking.BFS.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * highlight: concat String 太慢了，可以用StringBuffer
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        String res = root.val + "/";
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {

            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode cur = q.poll();
                if (cur.left == null) {
                    res += "null/";
                } else if (cur.left != null) {
                    res += cur.left.val + "/";
                    q.offer(cur.left);
                }

                if (cur.right == null) {
                    res += "null/";
                } else if (cur.right != null) {
                    res += cur.right.val + "/";
                    q.offer(cur.right);
                }

            }

        }
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }

        String[] parsedData = data.split("/");
        int index = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(parsedData[0]));
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            index++;
            if (index < parsedData.length) {
                if (parsedData[index].equals("null")) {
                    cur.left = null;
                } else {
                    cur.left = new TreeNode(Integer.parseInt(parsedData[index]));
                    q.offer(cur.left);
                }
            }

            index++;
            if (index < parsedData.length) {
                if (parsedData[index].equals("null")) {
                    cur.right = null;
                } else {
                    cur.right = new TreeNode(Integer.parseInt(parsedData[index]));
                    q.offer(cur.right);
                }
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));