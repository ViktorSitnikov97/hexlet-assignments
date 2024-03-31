package exercise;

// BEGIN
public final class Circle {
    private final Point point;
    private final int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getSquare() throws NegativeRadiusException {

        if (getRadius() < 0) {
            throw new NegativeRadiusException("error");
        }

        return Math.PI * this.radius * this.radius;

    }
}
// END
