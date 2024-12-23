package exercise;

// BEGIN

class Circle {
    Point ciPOint;
    int radius;

    Circle(Point ciPOint, int radius) {
        this.ciPOint = ciPOint;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("");
        }
        return Math.PI * radius * radius;
    }



}
// END
