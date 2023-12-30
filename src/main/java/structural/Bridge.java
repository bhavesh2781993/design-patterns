package structural;

/**
 * <img src="classpath:/templates/bridge.png">
 */
public class Bridge {
    public static void main(String[] args) {
        LivingThings dog = new Dog(new LandBreather());
        dog.breatheProcess();

        LivingThings fish = new Fish(new WaterBreather());
        fish.breatheProcess();

        // Here, LivingThings can Take any Breathable Dynamically.
        // Both, LivingThings (e.g. Dog, Fish, Tree) and Breathable (e.g. LandBreather, WaterBreather, TreeBreather) can grow independently
        LivingThings tree = new Tree(new TreeBreather());
        tree.breatheProcess();

        LivingThings customTree = new Tree(new CustomBreather());
        customTree.breatheProcess();
    }
}

abstract class LivingThings {
    Breathable breathable;

    public LivingThings(Breathable breathable) {
        this.breathable = breathable;
    }

    public void breatheProcess() {
        this.breathable.breathe();
    }
}

class Dog extends LivingThings {
    public Dog(Breathable breathable) {
        super(breathable);
    }
}

class Tree extends LivingThings {
    public Tree(Breathable breathable) {
        super(breathable);
    }
}

class Fish extends LivingThings {
    public Fish(Breathable breathable) {
        super(breathable);
    }
}

interface Breathable {
    void breathe();
}

class LandBreather implements Breathable {
    @Override
    public void breathe() {
        System.out.println("Land Breather");
    }
}

class WaterBreather implements Breathable {
    @Override
    public void breathe() {
        System.out.println("Water Breather");
    }
}

class TreeBreather implements Breathable {
    @Override
    public void breathe() {
        System.out.println("Tree Breather");
    }
}

class CustomBreather implements Breathable {
    @Override
    public void breathe() {
        System.out.println("Custom Breather");
    }
}
