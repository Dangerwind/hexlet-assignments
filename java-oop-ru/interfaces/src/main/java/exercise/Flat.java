package exercise;

// BEGIN
class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public String toString() {
        String temp;
        temp = "Квартира площадью " + getArea() + " метров на " + floor +" этаже";
        return temp;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        if (getArea() > another.getArea()) return 1;
        if (getArea() < another.getArea()) return -1;
        return 0;
    }

}

// END
