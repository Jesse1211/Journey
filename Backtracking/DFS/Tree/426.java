package Backtracking.DFS.Tree;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

class Solution {
    Node head;
    Node tail;

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;

        inorder(root);

        // Circular
        tail.right = head;
        head.left = tail;

        return head;
    }

    private void inorder(Node node) {
        if (node == null)
            return;

        inorder(node.left);

        if (head == null) { // first
            head = node;
            tail = head;
        } else {
            tail.right = node;
            node.left = tail;
            tail = node;
        }

        inorder(node.right);
    }
}