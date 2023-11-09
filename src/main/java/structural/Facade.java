package structural;

/**
 * Provides simplified interface for client to use.
 * This interface is connected with multiple service calls
 */
public class Facade {
    public static void main(String[] args) {
        FacadeInterface facadeInterface = new FacadeInterface();
        facadeInterface.doSomething();
    }
}

class FacadeInterface {
    Service1 s1 = new Service1();
    Service2 s2 = new Service2();
    Service3 s3 = new Service3();

    void doSomething() {
        s1.doSomething();
        s2.doSomething();
        s3.doSomething();
    }
}

class Service1 {
    void doSomething() {
        System.out.println("Service 1");
    }
}

class Service2 {
    void doSomething() {
        System.out.println("Service 2");
    }
}

class Service3 {
    void doSomething() {
        System.out.println("Service 3");
    }
}
