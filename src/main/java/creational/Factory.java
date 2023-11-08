package creational;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Hides implementation of object it creates.
 * Hides object type if return type is parent.
 * Example: LocalDateTime.now(), Path.get()
 */
public class Factory {
    public static void main(String[] args) {
        Vehicle v1 = CarFactory.create(CarType.AUDI);
        Vehicle v2 = CarFactory.create(CarType.BMW);
        System.out.println(v1);
        System.out.println(v2);

        // Returns concrete type
        LocalDateTime.now();

        // Returns parent type
        Path p1 = Paths.get("");
    }
}

interface Vehicle {

}

abstract class Car implements Vehicle {

}

class BMW extends Car {

}

class Audi extends Car {

}

class CarFactory {
    public static Vehicle create(CarType carType) {
        return switch (carType) {
            case BMW -> new BMW();
            case AUDI -> new Audi();
        };
    }
}

enum CarType {
    BMW,
    AUDI
}





