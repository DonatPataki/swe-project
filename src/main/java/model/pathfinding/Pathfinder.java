package model.pathfinding;

import model.util.Point;
import org.tinylog.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Pathfinder {
    private List<Node> open;
    private List<Node> closed;
    private final int[][] level;

    public Pathfinder(int[][] level) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.level = level;
    }

    public List<Node> findPath(Point startingPoint, Point endPoint) {
        this.open.clear();
        this.closed.clear();

        return calculatePath(searchPossiblePaths(startingPoint, endPoint), endPoint);
    }

    private Node searchPossiblePaths(Point startingPoint, Point endPoint) {
        Node current = new Node(null, startingPoint, 0, 0);
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

    private void neigbors(Node centerNode, Point endPoint) {
        Node node;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                Point neigborPosition = new Point(centerNode.position.getX() + x, centerNode.position.getY() + y);
                node = new Node(centerNode, neigborPosition, 0, 0);
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

    private double distance(Point a, Point b) {
            return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

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