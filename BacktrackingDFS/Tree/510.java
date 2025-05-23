package BacktrackingDFS.Tree;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};

class Solution {
    public Node inorderSuccessor(Node node) {
        // 左侧traverse过了, node要看右侧的最靠左
        if (node.right != null) {
            node = node.right;
            // 要优先左, 然后root
            while (node != null && node.left != null) {
                node = node.left;
            }
            return node;
        }

        // 左侧traverse过了, 但是右侧为空, 向上查找parent
        Node parent = node.parent;

        // 新的node必须是parent的右子树
        while (parent != null && parent.val < node.val) {
            parent = parent.parent;
        }

        return parent;
    }
}