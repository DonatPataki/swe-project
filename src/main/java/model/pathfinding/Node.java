package model.pathfinding;

import lombok.Data;
import model.util.Point;

/**
 * Helper class for {@link Pathfinder}.
 */
@Data
public class Node implements Comparable {
    private Node parent;
    private Point position;
    private double g;
    private double h;

    /**
     * Constructs a {@code Node} object.
     *
     * @param parent parent {@code Node} of the current node
     * @param position locatoin of the node
     */
    Node(Node parent, Point position) {
        this.parent = parent;
        this.position = position;
    }

    /**
     * Compares the current and another {@code Node}'s f value.
     *
     * @param o the {@code Node} we want to compare to
     * @return the result of the comparison
     */
    @Override
    public int compareTo(Object o) {
        Node otherNode = (Node) o;
        return (int) ((this.g + this.h) - (otherNode.g + otherNode.h));
    }

    /**
     * Checks if 2 {@code Node}s position is the same.
     *
     * @param o the {@code Node} we want to compare to
     * @return True if the position is the same. Otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Node))
            return  false;

        Node otherNode = (Node) o;
        return this.position.equals(otherNode.position);
    }
}