package model.util;

import lombok.Getter;

/**
 * Helper class for {@link model.level.LevelGenerator}.
 */
@Getter
public class Rectangle {

    private Point lowerLeftCoord;
    private Point upperRightCoord;

    /**
     * Constructs a {@code Rectangle} object.
     *
     * @param x x coordinate of the lower left corner
     * @param y y coordinate of the lower left corner
     * @param w width of the rectangle
     * @param h height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h) {
        this.lowerLeftCoord = new Point(x, y);
        this.upperRightCoord = new Point(x + w, y + h);
    }

    /**
     * Returns the {@link Point} of the center of the rectangle.
     *
     * @return the center {@Point}
     */
    public Point center() {
        return new Point((lowerLeftCoord.getX() + upperRightCoord.getX()) / 2, (lowerLeftCoord.getY() + upperRightCoord.getY()) / 2);
    }

    /**
     * Checks if the current rectangle and another rectangle is intersecting.
     *
     * @param otherRectangle the other rectangle
     * @return True if the rectangles intersect. Otherwise false.
     */
    public boolean intersect(Rectangle otherRectangle) {
        return (this.lowerLeftCoord.getX() <= otherRectangle.getUpperRightCoord().getX() &&
                this.upperRightCoord.getX() >= otherRectangle.getLowerLeftCoord().getX() &&
                this.lowerLeftCoord.getY() <= otherRectangle.getUpperRightCoord().getY() &&
                this.upperRightCoord.getY() >= otherRectangle.getLowerLeftCoord().getY());
    }

}
