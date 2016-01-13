/**
 * Created by johnwilliams on 1/8/16.
 *
 * Problematic design: graph data nodes directly contain the list of their children.
 * This makes it problematic to do a deepCopy of the graph.
 */
package simplegraph01;