package exercise;


import java.util.List;
// import java.util.stream.Collectors;


// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> homeList, int numOfHomes) {
        var myStrim = homeList.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .limit(numOfHomes)
                .toList();

        return myStrim;
    }

}

// END
