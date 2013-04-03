package payroll.transaction;


import org.junit.Before;
import payroll.repository.EmployeeRepositoryInstance;
import payroll.repository.InMemoryEmployeeRepository;

public class TransactionTest {
    TransactionExecutor transaction;

    @Before
    public void setUp() {
        transaction = new TransactionExecutor();
        EmployeeRepositoryInstance.set(new InMemoryEmployeeRepository());
    }
}
