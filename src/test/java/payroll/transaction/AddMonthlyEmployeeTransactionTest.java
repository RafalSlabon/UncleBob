package payroll.transaction;

import junit.framework.Assert;
import org.junit.Test;
import payroll.entity.Employee;
import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.MonthlySalary;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AddMonthlyEmployeeTransactionTest extends TransactionTest{

    @Test
    public void should_add_new_hourly_employee() throws Exception {
        AddMonthlyEmployeeRequest request = new AddMonthlyEmployeeRequest();
        request.empId = "1";
        request.name = "Rafal";
        request.address = "Wroclaw";
        request.salary = 1000;

        transaction = new AddMonthlyEmployeeTransaction(request);
        transaction.execute();

        Employee employee = EmployeeRepositoryInstance.get().getByEmpId("1");
        assertNotNull(employee);
        assertEquals(employee.getEmpId(), "1");
        assertEquals(employee.getName(), "Rafal");
        assertEquals(employee.getAddress(), "Wroclaw");
        Assert.assertEquals(((MonthlySalary) employee.getPayCalculator()).getSalary(), 1000, DELTA);
    }


}
