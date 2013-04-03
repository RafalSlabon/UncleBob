package payroll.transaction;

import org.junit.Test;
import payroll.Employee;
import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.MonthlySalary;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class TimeCardTransactionTest extends TransactionTest {

    @Test
    public void should_add_time_card_to_existing_hourly_rate_employee() throws Exception {
        Employee employee = new Employee();
        employee.empId = "1";
        employee.payCalculator = new HourlyRate();
        EmployeeRepositoryInstance.get().save(employee);

        transaction.execute("TimeCard <1> <2-10-2013> <2.5>");
        Employee employeeWithTimeCard = EmployeeRepositoryInstance.get().getById("1");
        HourlyRate hr = (HourlyRate) employeeWithTimeCard.payCalculator;
        assertNotNull(hr.getTimeCards().get(0));
    }

    @Test(expected = InvalidPayCalculator.class)
    public void should_get_error_when_existing_employee_is_not_hourly_rate() throws Exception {
        Employee employee = new Employee();
        employee.empId = "1";
        employee.payCalculator = new MonthlySalary();
        EmployeeRepositoryInstance.get().save(employee);

        transaction.execute("TimeCard <1> <2-10-2013> <2.5>");
    }

    @Test(expected = EmployeeNotExist.class)
    public void should_get_error_when_employee_not_exist() throws Exception {
        assertNull(EmployeeRepositoryInstance.get().getById("1"));
        transaction.execute("TimeCard <1> <2-10-2013> <2.5>");
    }
}
