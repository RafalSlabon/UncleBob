package payroll.transaction;

import junit.framework.Assert;
import org.junit.Test;
import payroll.entity.Employee;
import payroll.payCalculator.HourlyRate;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AddHourlyEmployeeTransactionTest extends TransactionTest{

    @Test
    public void should_add_new_hourly_employee() throws Exception {
        AddHourlyEmployeeRequest request = new AddHourlyEmployeeRequest();
        request.empId = "1";
        request.name = "Rafal";
        request.address = "Wroclaw";
        request.rate = 2.5;

        transaction = new AddHourlyEmployeeTransaction(request);
        transaction.execute();

        Employee employee = EmployeeRepositoryInstance.get().getByEmpId("1");
        assertNotNull(employee);
        assertEquals(employee.getEmpId(), "1");
        assertEquals(employee.getName(), "Rafal");
        assertEquals(employee.getAddress(), "Wroclaw");
        Assert.assertEquals(((HourlyRate) employee.getPayCalculator()).getRate(), 2.5, DELTA);
    }


}
