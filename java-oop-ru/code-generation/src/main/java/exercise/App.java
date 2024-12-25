package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
class App {
    public static void save(Path filepath, Car myCar) {
        try {
            Files.writeString(filepath, myCar.serialize());
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }
    }
    public static Car extract(Path filepath) throws IOException {
        String strFull = new String(Files.readAllBytes(filepath));
        return Car.deserialize(strFull);
    }
}
// END
