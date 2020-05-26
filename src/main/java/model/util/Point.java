package model.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a 2 dimensional point in a grid.
 */
@Getter
@Setter
public class Point {

    private int x;
    private int y;

    /**
     * Constructs a {@code Point} object.
     *
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Compares 2 {@code Point} object.
     *
     * @param o other {@code Point} object
     * @return True if the x and y coordinates are the same. Otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  Point))
            return false;

        Point otherPoint = (Point) o;
        return this.x == otherPoint.getX() && this.y == otherPoint.getY();
    }
}
