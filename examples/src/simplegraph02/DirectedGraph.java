package simplegraph02;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by johnwilliams on 1/8/16.
 */
public class DirectedGraph {
    private final Map<MutableGraphNode,Set<MutableGraphNode>> nodes = new HashMap<>();

    public void addNode(MutableGraphNode node) {
        if (!nodes.containsKey(node)) {
            nodes.put(node, new HashSet<>());
        }
    }

    public void addEdge(MutableGraphNode from, MutableGraphNode to) {
        addNode(from);
        addNode(to);
        Set<MutableGraphNode> children = nodes.get(from);
        children.add(to);
    }

    public DirectedGraph deepCopy() {
        DirectedGraph dg = new DirectedGraph();
        Map<MutableGraphNode, MutableGraphNode> copies = new HashMap<>();

        for (MutableGraphNode original : nodes.keySet()) {
            copies.put(original, original.deepCopy());
        }
        for (MutableGraphNode original : nodes.keySet()) {
            Set<MutableGraphNode> children = nodes.get(original);
            MutableGraphNode copy = copies.get(original);
            if (children.isEmpty()) {
                dg.addNode(copy);
            }
            else {
                for (MutableGraphNode child : children) {
                    dg.addEdge(copy, copies.get(child));
                }
            }
        }

        return dg;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n", "[","]");
        for (MutableGraphNode node : nodes.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.id());
            StringJoiner childJoiner = new StringJoiner(",", "->[", "]");
            for (MutableGraphNode child :  nodes.get(node)) {
                childJoiner.add(child.id());
            }
            sb.append(childJoiner.toString());
            sj.add(sb.toString());
        }
        return sj.toString();
    }

    public static void main(String[] args) {

        DirectedGraph g = new DirectedGraph();

        MutableGraphNode a = new MutableGraphNode("A");
        MutableGraphNode b = new MutableGraphNode("B");
        MutableGraphNode c = new MutableGraphNode("C");
        MutableGraphNode d = new MutableGraphNode("D");

        g.addEdge(a,b);
        g.addEdge(a,a);
        g.addEdge(c,a);
        g.addNode(d);

        DirectedGraph g2 = g.deepCopy();

        // Rename the original nodes, verify the deep copied graph doesn't change.

        a.name = "A'";

        System.out.println(g);
        System.out.println(g2);
    }
}