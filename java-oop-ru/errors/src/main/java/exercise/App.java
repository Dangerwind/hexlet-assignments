package exercise;

// BEGIN
class App {
    public static void printSquare(Circle circle) {
        try {
            double sq = circle.getSquare();
            System.out.println(Math.round(sq));
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }


    }
}

// END
