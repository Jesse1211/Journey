package BacktrackingDFS.Tree;

import java.util.HashSet;
import java.util.Set;

// meta

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        while (p != null) {
            if (searchDown(p, q))
                return p;
            p = p.parent;
        }
        return null;
    }

    private boolean searchDown(Node root, Node target) {
        if (root == null)
            return false;
        if (root == target)
            return true;
        return searchDown(root.left, target) || searchDown(root.right, target);
    }

}

class Solution1 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pVisited = new HashSet<>();
        Set<Node> qVisited = new HashSet<>();

        while (p != q) {
            if (pVisited.contains(q)) {
                return q;
            } else if (qVisited.contains(p)) {
                return p;
            }

            pVisited.add(p);
            qVisited.add(q);
            if (p.parent != null)
                p = p.parent;
            if (q.parent != null)
                q = q.parent;
        }

        if (q != null) {
            return q;
        }
        return p;
    }
}