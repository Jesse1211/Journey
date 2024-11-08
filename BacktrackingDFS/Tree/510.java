package BacktrackingDFS.Tree;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};

class Solution {
    public Node inorderSuccessor(Node node) {
        // check right children
        if (node.right != null) {
            node = node.right;
            // 要优先左, 然后root
            while (node != null && node.left != null) {
                node = node.left;
            }
            return node;
        }

        // find the parent
        Node parent = node.parent;

        // node必须是parent的左子树
        while (parent != null && parent.val < node.val) {
            parent = parent.parent;
        }

        return parent;
    }
}