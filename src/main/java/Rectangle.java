import lombok.Getter;

@Getter
public class Rectangle {

    private Point lowerLeftCoord;
    private Point upperRightCoord;

    public Rectangle(int x, int y, int w, int h) {
        this.lowerLeftCoord = new Point(x, y);
        this.upperRightCoord = new Point(x +w, y + h);
    }

    public Point center() {
        Point center = new Point((lowerLeftCoord.getX() + upperRightCoord.getX()) / 2, (lowerLeftCoord.getY() + upperRightCoord.getY()) / 2);
        return center;
    }

    public boolean intersect(Rectangle otherRectangle) {
        return (this.lowerLeftCoord.getX() <= otherRectangle.getUpperRightCoord().getX() &&
                this.upperRightCoord.getX() >= otherRectangle.getLowerLeftCoord().getX() &&
                this.lowerLeftCoord.getY() <= otherRectangle.getUpperRightCoord().getY() &&
                this.upperRightCoord.getY() >= otherRectangle.getLowerLeftCoord().getY());
    }

}
