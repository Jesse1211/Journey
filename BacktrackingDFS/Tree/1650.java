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
            if (dfs(p, q))
                return p;
            p = p.parent;
        }
        return null;
    }

    private boolean dfs(Node parent, Node q) { // if parent has q
        if (parent == q) {
            return true;
        } else if (parent == null) {
            return false;
        }

        return dfs(parent.left, q) || dfs(parent.right, q);
    }
}

class Solution2 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> qVisited = new HashSet<>();
        Set<Node> pVisited = new HashSet<>();
        qVisited.add(q);
        pVisited.add(p);

        while (q != null && p != null) {

            if (qVisited.contains(p)) {
                return p;
            } else if (pVisited.contains(q)) {
                return q;
            } else if (q == p) {
                return p;
            }

            if (q.parent != null)
                q = q.parent;
            if (p.parent != null)
                p = p.parent;
            qVisited.add(q);
            pVisited.add(p);
        }

        return null;
    }
}