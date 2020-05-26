package model.pathfinding;

import model.level.Level;
import model.util.Point;
import org.tinylog.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of A* search algorithm to find the optimal path between 2 point.
 */
public class Pathfinder {
    private List<Node> open;
    private List<Node> closed;
    private final int[][] level;

    /**
     * Constructs a {@code Pathfinder} object
     *
     * @param level a {@link Level} where we want to find a path
     */
    public Pathfinder(Level level) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.level = level.getLayout();
    }

    /**
     * Searches for optimal path between 2 {@link Point}s on the level.
     *
     * @param startingPoint {@link model.player.Player} location
     * @param endPoint the {@link Point} where we want to get the character
     * @return If the end point is accessible returns a list of {@link Node} which represents the optimal path.
     * If no path can be taken returns an empty list.
     */
    public List<Node> findPath(Point startingPoint, Point endPoint) {
        this.open.clear();
        this.closed.clear();

        return calculatePath(searchPossiblePaths(startingPoint, endPoint), endPoint);
    }

    /**
     * A* search loop
     *
     * @param startingPoint starting {@link Point} of the search
     * @param endPoint end {@link Point} of the search
     * @return a possible {@link Node} to get the optimal path
     */
    private Node searchPossiblePaths(Point startingPoint, Point endPoint) {
        Node current = new Node(null, startingPoint);
        closed.add(current);
        neigbors(current, endPoint);

        while (!open.isEmpty()) {
            if (current.position.equals(endPoint))
                break;

            current = open.get(0);
            open.remove(0);
            closed.add(current);
            neigbors(current, endPoint);
        }
        return current;
    }

    /**
     * Adds neigbors of a {@link Node} to the open list.
     *
     * @param centerNode the {@link Node} which neigbors we interested in
     * @param endPoint end {@link Point} of the search
     */
    private void neigbors(Node centerNode, Point endPoint) {
        Node node;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                Point neigborPosition = new Point(centerNode.position.getX() + x, centerNode.position.getY() + y);
                node = new Node(centerNode, neigborPosition);
                if ((x != 0 || y != 0)
                        && node.position.getX() > -1 && node.position.getX() < this.level[0].length
                        && node.position.getY() > -1 && node.position.getY() < this.level.length
                        && this.level[node.position.getY()][node.position.getX()] != 1
                        && !this.open.contains(node) && !this.closed.contains(node)) {
                    node.g = node.parent.g + distance(node.position, node.parent.position);
                    node.h = distance(node.position, endPoint);

                    this.open.add(node);
                }
            }
        }
        Collections.sort(this.open);
    }

    /**
     * Returns the distance between 2 {@link Point}s.
     *
     * @param a one {@link Point}
     * @param b another {@link Point}
     * @return the distance
     */
    private double distance(Point a, Point b) {
            return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    /**
     * Returns the result of the search.
     *
     * @param node a {@link Node} from which we can get back to the sarting location
     * @param endPoint end {@link Point} of the search
     * @return If optimal path exits between 2 points returns the List of {@link Node}s to get there. Otherwise returns an empty list
     */
    private List<Node> calculatePath(Node node, Point endPoint) {
        if (!node.position.equals(endPoint)) {
            Logger.debug("no path found");
            return new ArrayList<>();
        }

        List<Node> path = new ArrayList<>();
        path.add(0, node);
        while (node.parent != null) {
            node = node.parent;
            path.add(0, node);
        }
        Logger.debug("path found");
        return path;
    }
}