package exercise;

// BEGIN
public class Cottage implements Home {
    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home o) {
        return Double.compare(this.getArea(), o.getArea());
    }

    @Override
    public String toString() {
        return floorCount
                + " этажный коттедж площадью "
                + this.getArea()
                + " метров";
    }
}
// END
