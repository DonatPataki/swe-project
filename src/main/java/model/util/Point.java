package model.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  Point))
            return false;

        Point otherPoint = (Point) o;
        return this.x == otherPoint.getX() && this.y == otherPoint.getY();
    }
}
