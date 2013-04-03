package payroll.transaction;


import org.junit.Test;
import payroll.Employee;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class DelEmployeeTransactionTest extends TransactionTest {

    @Test
    public void should_deleted_existing_employee() throws Exception {
        Employee existingEmployee = new Employee();
        existingEmployee.empId = "1";
        EmployeeRepositoryInstance.get().save(existingEmployee);
        assertNotNull(EmployeeRepositoryInstance.get().getById("1"));

        transaction.execute("DelEmp <1>");
        assertNull(EmployeeRepositoryInstance.get().getById("1"));
    }

    @Test(expected = EmployeeNotExist.class)
    public void should_get_error_when_employee_does_not_exist() throws Exception {
        assertNull(EmployeeRepositoryInstance.get().getById("666"));
        transaction.execute("DelEmp <666>");
    }

}
