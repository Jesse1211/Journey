package BFS.Tree;

import java.util.ArrayDeque;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Codec {

    public void postorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            // sb.append("null/");
            return;
        }

        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val + "/");
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] list = data.split("/");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 可以用stack, 也可以用queue, 这里ArrayDeque是因为要把get & remove分开执行,
        // 因为我们要get后检查boundary来分辨是否要remove
        for (String str : list) {
            stack.offer(Integer.parseInt(str));
        }

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    public TreeNode helper(int min, int max, ArrayDeque<Integer> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        int cur = stack.getLast();

        if (cur < min || cur > max) { // cur不是当前path的值
            return null;
        }

        stack.removeLast();

        TreeNode root = new TreeNode(cur);
        root.right = helper(root.val, max, stack);
        root.left = helper(min, root.val, stack);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;