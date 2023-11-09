package behavioral;

/**
 * Provides a way to go through each element in a group of elements and hides the traversal implementation.
 * Example, ResultSet from JDBC, Collection Iterator
 */
public class Iterator {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4};
        Container container = new Container(arr);
        while (container.hasNext()) {
            final int element = container.next();
            System.out.println(element);
        }

        System.out.println("=========");

        while (container.hasPrevious()) {
            final int element = container.previous();
            System.out.println(element);
        }
    }
}

interface CustomIterator {
    boolean hasNext();
    int next();
    int get();
    boolean hasPrevious();
    int previous();
}

class Container implements CustomIterator {
    private int cursor = -1;
    private int[] arr;

    public Container(int[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return cursor < arr.length - 1;
    }

    @Override
    public int next() {
        cursor++;
        return arr[cursor];
    }

    @Override
    public int get() {
        return arr[cursor];
    }

    @Override
    public boolean hasPrevious() {
        return cursor >= 0;
    }

    @Override
    public int previous() {
        int element = arr[cursor];
        cursor--;
        return element;
    }
}
