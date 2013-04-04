package payroll.transaction;

import junit.framework.Assert;
import org.junit.Test;
import payroll.entity.Employee;
import payroll.payCalculator.CommissionRate;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AddCommissionedEmployeeTransactionTest extends TransactionTest{

    @Test
    public void should_add_new_commissioned_employee() throws Exception {
        AddCommissionedEmployeeRequest request = new AddCommissionedEmployeeRequest();
        request.empId = "1";
        request.name = "Rafal";
        request.address = "Wroclaw";
        request.salary = 1000;
        request.rate = 2.5;

        transaction = new AddCommissionedEmployeeTransaction(request);
        transaction.execute();

        Employee employee = EmployeeRepositoryInstance.get().getByEmpId("1");
        assertNotNull(employee);
        assertEquals(employee.getEmpId(), "1");
        assertEquals(employee.getName(), "Rafal");
        assertEquals(employee.getAddress(), "Wroclaw");
        assertEquals(((CommissionRate) employee.getPayCalculator()).getSalary(), 1000, DELTA);
        assertEquals(((CommissionRate) employee.getPayCalculator()).getRate(), 2.5, DELTA);
    }


}
