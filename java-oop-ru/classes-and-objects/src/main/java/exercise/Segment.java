package exercise;

// BEGIN
class Segment {
    private static Point point1;
    private static Point point2;
    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public static Point getBeginPoint() {
       return point1;
    }
    public static Point getEndPoint() {
        return point2;

    }
    public static Point getMidPoint() {
        int midX = (point1.getX() + point2.getX())/2;
        int midY = (point1.getY() + point2.getY())/2;
        var newPoint = new Point(midX, midY);
        return newPoint;
    }
}
// END
