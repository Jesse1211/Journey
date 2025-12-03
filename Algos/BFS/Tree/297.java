package BFS.Tree;

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

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        sb.append(root.val + "/");

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            if (cur.left != null) {
                sb.append(cur.left.val + "/");
                q.offer(cur.left);
            } else {
                sb.append("null/");
            }

            if (cur.right != null) {
                sb.append(cur.right.val + "/");
                q.offer(cur.right);
            } else {
                sb.append("null/");
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isBlank()) {
            return null;
        }

        String[] nodeList = data.split("/");
        TreeNode root = new TreeNode(Integer.parseInt(nodeList[0]));
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int index = 0;

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            index++;
            if (!nodeList[index].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodeList[index]));
                cur.left = left;
                q.offer(left);
            }
            index++;
            if (!nodeList[index].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(nodeList[index]));
                cur.right = right;
                q.offer(right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));