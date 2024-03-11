package exercise;

// BEGIN
public class Segment {

    private final Point point1;
    private final Point point2;

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return this.point1;
    }
    public Point getEndPoint() {
        return this.point2;
    }

    public Point getMidPoint() {
        int xMiddle = (this.point1.getX() + this.point2.getX()) / 2;
        int yMiddle = (this.point1.getY() + this.point2.getY()) / 2;
        return new Point(xMiddle, yMiddle);
    }
}
// END
