package simplegraph02;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by johnwilliams on 1/8/16.
 */
public class MutableGraphNode {
    public String name;

    MutableGraphNode(String name) { this.name = name; }

    MutableGraphNode deepCopy() {
        return new MutableGraphNode(name);
    }

    public String id() {
        //return new StringBuilder().append(name).append('(').append(super.toString()).append(')').toString();
        return name;
    }

    @Override
    public String toString() {

        return id();
    }
}
