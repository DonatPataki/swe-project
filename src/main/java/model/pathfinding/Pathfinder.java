package model.pathfinding;

import model.level.Level;
import model.util.Point;
import org.tinylog.Logger;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of A* search algorithm to find the optimal path between 2 point.
 */
public class Pathfinder {
    private List<Node> open;
    private List<Node> closed;
    private final int[][] level;

    /**
     * Constructs a {@code Pathfinder} object.
     *
     * @param level a {@link Level} where we want to find a path
     */
    public Pathfinder(Level level) {
        open = new ArrayList<>();
        closed = new ArrayList<>();
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
    public List<Point> findPath(Point startingPoint, Point endPoint) {
        open.clear();
        closed.clear();

        return calculatePath(searchPossiblePaths(startingPoint, endPoint), endPoint);
    }

    /**
     * A* search loop.
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
            if (current.getPosition().equals(endPoint))
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
                Point neigborPosition = new Point(centerNode.getPosition().getX() + x, centerNode.getPosition().getY() + y);
                node = new Node(centerNode, neigborPosition);
                if ((x != 0 || y != 0)
                        && node.getPosition().getX() > -1 && node.getPosition().getX() < level[0].length
                        && node.getPosition().getY() > -1 && node.getPosition().getY() < level.length
                        && level[node.getPosition().getY()][node.getPosition().getX()] > 0
                        && !open.contains(node) && !closed.contains(node)) {
                    node.setG(node.getParent().getG() + distance(node.getPosition(), node.getParent().getPosition()));
                    node.setH(distance(node.getPosition(), endPoint));

                    open.add(node);
                }
            }
        }
        open.sort(Node::compareTo);
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
     * @return If optimal path exits between 2 points returns the List of {@link Point}s to get there. Otherwise returns an empty list
     */
    private List<Point> calculatePath(Node node, Point endPoint) {
        if (!node.getPosition().equals(endPoint)) {
            Logger.debug("no path found");
            return new ArrayList<>();
        }

        List<Point> path = new ArrayList<>();
        path.add(0, node.getPosition());
        while (node.getParent() != null) {
            node = node.getParent();
            path.add(0, node.getPosition());
        }
        path.remove(0);
        Logger.debug("path found");
        return path;
    }
}