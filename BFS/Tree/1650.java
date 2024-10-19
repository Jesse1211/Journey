package BFS.Tree;

import java.util.HashSet;
import java.util.Set;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pVisited = new HashSet<>();
        Set<Node> qVisited = new HashSet<>();

        pVisited.add(p);
        qVisited.add(q);
        while (q != p) {

            if (qVisited.contains(p)) {
                return p;
            } else if (pVisited.contains(q)) {
                return q;
            }

            pVisited.add(p);
            qVisited.add(q);

            if (p.parent != null) {
                p = p.parent;
            }
            if (q.parent != null) {
                q = q.parent;
            }
        }

        if (q == null) {
            return p;
        }
        return q;
    }
}