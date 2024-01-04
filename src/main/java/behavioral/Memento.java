package behavioral;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    public static void main(String[] args) {
        ConfigurationCareTaker careTaker = new ConfigurationCareTaker();

        ConfigurationOriginator originator = new ConfigurationOriginator(1, 2);
        final ConfigurationMemento memento1 = originator.createMemento();
        careTaker.addMemento(memento1);
        System.out.println("Original: " + originator);

        originator.setHeight(3);
        originator.setWidth(4);
        final ConfigurationMemento memento2 = originator.createMemento();
        careTaker.addMemento(memento2);
        System.out.println("Modified: " + originator);

        originator.setHeight(5);
        originator.setWidth(6);
        System.out.println("Wrongly Modified without Create / Add of Memento: " + originator);

        final ConfigurationMemento lastMemento = careTaker.undo();
        originator.restoreMemento(lastMemento);
        System.out.println("After Undo: " + originator);

        final ConfigurationMemento lastToLastMemento = careTaker.undo();
        originator.restoreMemento(lastToLastMemento);
        System.out.println("After 2 Undo: " + originator);

        final ConfigurationMemento lastToLastToLastMemento = careTaker.undo();
        originator.restoreMemento(lastToLastToLastMemento);
        System.out.println("After 3 Undo: " + originator);
    }
}

class ConfigurationOriginator {
    private int height;
    private int width;

    public ConfigurationOriginator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ConfigurationMemento createMemento() {
        return new ConfigurationMemento(this.height, this.width);
    }

    public void restoreMemento(ConfigurationMemento memento) {
        if (memento != null) {
            this.height = memento.getHeight();
            this.width = memento.getWidth();
        } else {
            System.out.println("Not restored due to null memento");
        }
    }

    @Override
    public String toString() {
        return "ConfigurationOriginator{" +
            "height=" + height +
            ", width=" + width +
            '}';
    }
}

class ConfigurationMemento {
    private int height;
    private int width;

    public ConfigurationMemento(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

class ConfigurationCareTaker {
    List<ConfigurationMemento> history;

    public ConfigurationCareTaker() {
        this.history = new ArrayList<>();
    }

    public void addMemento(ConfigurationMemento memento) {
        history.add(memento);
    }

    public ConfigurationMemento undo() {
        if (!history.isEmpty()) {
            var lastMementoIndex = history.size() - 1;
            return history.remove(lastMementoIndex);
        }
        return null;
    }

}
