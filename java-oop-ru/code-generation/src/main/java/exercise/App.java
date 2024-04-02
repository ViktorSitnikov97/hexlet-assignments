package exercise;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
//    public static void main(String[] args) {
//        try {
//            User owner = new User(1, "Ivan", "P", 25);
//            // Вызываем автоматически сгенерированный геттер
//            System.out.println(owner.getFirstName()); // "Ivan"
//            Car car = new Car(1, "bmv", "x5", "black", owner);
//            String jsonRepresentation = car.serialize();
//            System.out.println(jsonRepresentation);
//            Car instance = Car.unserialize(jsonRepresentation);
//            System.out.println(instance.getBrand()); // "bmv"
//            System.out.println(instance.getModel()); // "x5"
//
//            User owner = new User(1, "Ivan", "P", 25);
//            Car car1 = new Car(1, "audi", "q3", "black", owner);
//            Path tempPath = Files.createTempFile("temp", "");
//            App.save(tempPath, car1);
//            Car car2 = App.extract(tempPath); // Возвращает инстанс класса Car
//            System.out.println(car2.getModel()); // "passat"
//            Files.deleteIfExists(tempPath);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    public static void save(Path tempPath, Car car) {
        try (OutputStream out = Files.newOutputStream(tempPath, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {
            out.write(car.serialize().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Car extract(Path tempPath) {
        try {
            return Car.unserialize(Files.readString(tempPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
