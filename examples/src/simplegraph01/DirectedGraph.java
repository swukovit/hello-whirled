package simplegraph01;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.HashMap;

/**
 * Created by johnwilliams on 1/8/16.
 */
public class DirectedGraph {
    private final Set<MutableGraphNode> nodes = new HashSet<>();

    public void addNode(MutableGraphNode node) {
        nodes.add(node);
    }

    public void addEdge(MutableGraphNode from, MutableGraphNode to) {
        addNode(from);
        addNode(to);
        from.children.add(to);
    }

    public DirectedGraph deepCopy() {
        throw new UnsupportedOperationException();
    } //TODO

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",\n", "[","]");
        for (MutableGraphNode node : nodes) {
            sj.add(node.toString());
        }
        return sj.toString();
    }

    public static void main(String[] args) {

        DirectedGraph g = new DirectedGraph();

        MutableGraphNode a = new MutableGraphNode("A");
        MutableGraphNode b = new MutableGraphNode("B");
        MutableGraphNode c = new MutableGraphNode("C");
        MutableGraphNode d = new MutableGraphNode("D");

        g.addNode(a);
        g.addEdge(a,b);
        g.addEdge(a,a);
        g.addEdge(c,a);
        g.addNode(d);

        DirectedGraph g2 = g.deepCopy();

        // Rename the original nodes, verify the deep copied graph doesn't change.

        a.name = "renamed A";

        System.out.println(g);
        System.out.println(g2);
    }
}