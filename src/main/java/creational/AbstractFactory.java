package creational;

/**
 * This is a factory of factory
 */
public class AbstractFactory {
    public static void main(String[] args) {
        // This returns factory
        Vehicle1Factory bikeFactory = Vehicle1Factory.create(Vehicle1Type.BIKE);

        // This uses above factory to create object;
        final Vehicle1 bike = bikeFactory.create();
        System.out.println(bike.getClass());

        final Vehicle1Factory carFactory = Vehicle1Factory.create(Vehicle1Type.CAR);
        final Vehicle1 car = carFactory.create();
        System.out.println(car.getClass());

        // EntityManagerFactory emf = Persistence.createEntityManagerFactory();
        // EntityManager em = emf.createEntityManager();
    }
}

enum Vehicle1Type {
    CAR, BIKE
}

interface Vehicle1 {

}

interface Vehicle1Factory {

    Vehicle1 create();
    static Vehicle1Factory create(Vehicle1Type vehicle1Type) {
        return switch (vehicle1Type) {
            case CAR -> new Car1Factory();
            case BIKE -> new Bike1Factory();
        };
    }
}

class Car1 implements Vehicle1 {

}

class Car1Factory implements Vehicle1Factory {
    @Override
    public Vehicle1 create() {
        return new Car1();
    }
}

class Bike1 implements Vehicle1 {

}

class Bike1Factory implements Vehicle1Factory {

    @Override
    public Vehicle1 create() {
        return new Bike1();
    }
}


