package creational;

import java.io.Serial;
import java.io.Serializable;

/**
 * Prevent singleton breakage from cloning, deserialization, reflection
 */
public class Singleton implements Serializable, Cloneable {

    private static Singleton instance = null;

    // Private Constructor
    private Singleton() {
        // Prevent Access through reflection
        if (instance != null) {
            throw new RuntimeException("Instance already present");
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            // Prevent Multi Thread access
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Prevents Object creation on Object deserialization
    @Serial
    public Object readResolve() {
        return instance;
    }

    // Prevents Object cloning, This is not required as by default clone is not available. Added here just for readability
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
