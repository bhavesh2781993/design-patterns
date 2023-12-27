package creational;

public class Prototype {
    public static void main(String[] args) {
        Test test = new Test("Hello", 123);
        System.out.println(test);

        Test test1 = (Test) test.clone();
        System.out.println(test1);
    }
}

interface CustomCloneable {
    CustomCloneable clone();
}

class Test implements CustomCloneable {
    private String field1;
    private int field2;

    public Test(String field1, int field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @Override
    public CustomCloneable clone() {
        return new Test(this.field1, this.field2);
    }

    @Override
    public String toString() {
        return "Test{" +
            "field1='" + field1 + '\'' +
            ", field2=" + field2 +
            '}';
    }
}