package payroll.transaction;

import junit.framework.Assert;
import org.junit.Test;
import payroll.Employee;
import payroll.payCalculator.CommissionRate;
import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.MonthlySalary;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static payroll.transaction.TransactionExecutor.InvalidTransactionName;
import static payroll.transaction.TransactionParts.InvalidValue;
import static payroll.transaction.TransactionParts.NotEnoughParts;


public class AddEmployeeTransactionTest extends TransactionTest {
    public static final double DELTA = .001;

    @Test
    public void should_add_new_hourly_employee() throws Exception {
        String text = "AddEmp <1> \"Rafal\" \"Wroclaw\" H <2.5>";
        transaction.execute(text);
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertNotNull(employee);
        assertEquals(employee.empId, "1");
        assertEquals(employee.name, "Rafal");
        assertEquals(employee.address, "Wroclaw");
        Assert.assertEquals(((HourlyRate) employee.payCalculator).getRate(), 2.5, DELTA);
    }

    @Test(expected = InvalidTransactionName.class)
    public void should_get_error_message_when_transaction_name_is_invalid() throws Exception {
        String text = "XXX <0> \"ANY NAME\" \"ANY ADDRESS\" H <0.0>";
        transaction.execute(text);
    }

    @Test(expected = InvalidValue.class)
    public void should_get_error_message_when_employee_id_is_invalid() throws Exception {
        String text = "AddEmp XXX \"ANY NAME\" \"ANY ADDRESS\" H <0.0>";
        transaction.execute(text);
    }

    @Test(expected = NotEnoughParts.class)
    public void should_get_error_message_when_employee_id_is_missing() throws Exception {
        String text = "AddEmp";
        transaction.execute(text);
    }

    @Test(expected = NotEnoughParts.class)
    public void should_get_error_message_when_name_is_missing() throws Exception {
        String text = "AddEmp <0>";
        transaction.execute(text);
    }

    @Test(expected = NotEnoughParts.class)
    public void should_get_error_message_when_address_is_missing() throws Exception {
        String text = "AddEmp <0> \"ANY NAME\"";
        transaction.execute(text);
    }

    @Test(expected = NotEnoughParts.class)
    public void should_get_error_message_when_pay_type_is_missing() throws Exception {
        String text = "AddEmp <0> \"ANY NAME\" \"ANY ADDRESS\"";
        transaction.execute(text);
    }

    @Test(expected = NotEnoughParts.class)
    public void should_get_error_message_when_rate_for_hourly_type_is_missing() throws Exception {
        String text = "AddEmp <0> \"ANY NAME\" \"ANY ADDRESS\" H";
        transaction.execute(text);
    }

    @Test
    public void should_add_new_monthly_salary_employee() throws Exception {
        String text = "AddEmp <1> \"Rafal\" \"Wroclaw\" S <99.99>";
        transaction.execute(text);
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertNotNull(employee);
        assertEquals(employee.empId, "1");
        assertEquals(employee.name, "Rafal");
        assertEquals(employee.address, "Wroclaw");
        Assert.assertEquals(((MonthlySalary) employee.payCalculator).getSalary(), 99.99, DELTA);
    }

    @Test
    public void should_add_new_commission_rate_employee() throws Exception {
        String text = "AddEmp <1> \"Rafal\" \"Wroclaw\" C <99.99> <12.5>";
        transaction.execute(text);
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertNotNull(employee);
        assertEquals(employee.empId, "1");
        assertEquals(employee.name, "Rafal");
        assertEquals(employee.address, "Wroclaw");
        Assert.assertEquals(((CommissionRate) employee.payCalculator).getSalary(), 99.99, DELTA);
        assertEquals(((CommissionRate) employee.payCalculator).getRate(), 12.5, DELTA);
    }
}
