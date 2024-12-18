package exercise;

// BEGIN
class Cottage implements Home{
    private double area;
    private int floorCount;
    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public String toString() {
        String temp;
        temp = floorCount + " этажный коттедж площадью " + area + " метров";
        return temp;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        if (getArea() > another.getArea()) return 1;
        if (getArea() < another.getArea()) return -1;
        return 0;
    }
}

// END
