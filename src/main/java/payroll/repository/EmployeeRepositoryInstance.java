package payroll.repository;

public class EmployeeRepositoryInstance {

    public static EmployeeRepository INSTANCE = new InMemoryEmployeeRepository();

    public static EmployeeRepository get() {
        return INSTANCE;
    }

    public static void set(EmployeeRepository instance) {
        INSTANCE = instance;
    }

    private EmployeeRepositoryInstance() {
    }
}
