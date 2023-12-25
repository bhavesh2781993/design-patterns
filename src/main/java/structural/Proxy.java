package structural;

public class Proxy {
    public static void main(String[] args) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        employeeDao.create();
        System.out.println("======");
        employeeDao.get();
        System.out.println("======");
        employeeDao.delete();
        System.out.println("======");
    }
}

interface EmployeeDao {
    void create();
    void get();
    void delete();

    static EmployeeDao getInstance() {
        return new EmployeeDaoProxy();
    }
}

class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void create() {
        System.out.println("Created in DB");
    }

    @Override
    public void get() {
        System.out.println("Got from DB");
    }

    @Override
    public void delete() {
        System.out.println("Deleted from DB");
    }
}

class EmployeeDaoProxy implements EmployeeDao {
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    @Override
    public void create() {
        System.out.println("Proxy Create");
        employeeDao.create();
    }

    @Override
    public void get() {
        System.out.println("Proxy Get");
        employeeDao.get();
    }

    @Override
    public void delete() {
        System.out.println("Proxy Delete");
        employeeDao.delete();
    }
}