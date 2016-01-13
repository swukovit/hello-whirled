package simplegraph01;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by johnwilliams on 1/8/16.
 */
public class MutableGraphNode {
    public String name;
    final List<MutableGraphNode> children = new LinkedList<>();

    MutableGraphNode(String name) { this.name = name; }

    public String id() {
        return new StringBuilder().append(name).append('(').append(super.toString()).append(')').toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id());

        StringJoiner sj = new StringJoiner(",","->[","]");
        for (MutableGraphNode child : children) {
            sj.add(child.id());
        }
        sb.append(sj.toString());

        return sb.toString();
    }
}
