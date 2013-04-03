package payroll.transaction;

import org.junit.Test;
import payroll.Employee;
import payroll.payCalculator.CommissionRate;
import payroll.payCalculator.MonthlySalary;
import payroll.repository.EmployeeRepositoryInstance;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class SalesReceiptTransactionTest extends TransactionTest {

    @Test
    public void should_add_sale_receipt_to_existing_commission_rate_employee() throws Exception {
        Employee employee = new Employee();
        employee.empId = "1";
        employee.payCalculator = new CommissionRate();
        EmployeeRepositoryInstance.get().save(employee);

        transaction.execute("SaleReceipt <1> <2-10-2013> <5>");
        Employee employeeWithTimeCard = EmployeeRepositoryInstance.get().getById("1");
        CommissionRate cr = (CommissionRate) employeeWithTimeCard.payCalculator;
        assertNotNull(cr.getSaleReceipts().get(0));
    }

    @Test(expected = InvalidPayCalculator.class)
    public void should_get_error_when_existing_employee_is_not_commission_rate() throws Exception {
        Employee employee = new Employee();
        employee.empId = "1";
        employee.payCalculator = new MonthlySalary();
        EmployeeRepositoryInstance.get().save(employee);

        transaction.execute("SaleReceipt <1> <2-10-2013> <5>");
    }

    @Test(expected = EmployeeNotExist.class)
    public void should_get_error_when_employee_not_exist() throws Exception {
        assertNull(EmployeeRepositoryInstance.get().getById("1"));
        transaction.execute("SaleReceipt <1> <2-10-2013> <5>");
    }
}
