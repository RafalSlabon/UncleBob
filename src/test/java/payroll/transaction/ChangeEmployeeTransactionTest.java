package payroll.transaction;

import org.junit.Before;
import org.junit.Test;
import payroll.Employee;
import payroll.payCalculator.CommissionRate;
import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.MonthlySalary;
import payroll.repository.EmployeeRepositoryInstance;
import payroll.union.UnionMembership;
import payroll.union.UnionMemberships;

import static junit.framework.Assert.*;
import static payroll.transaction.ChangeEmployeeTransaction.ChangeActionPartIsMissing;
import static payroll.transaction.ChangeEmployeeTransaction.EmployeeAlreadyInUnionMember;
import static payroll.transaction.ChangeEmployeeTransaction.UnknownChangeAction;

public class ChangeEmployeeTransactionTest extends TransactionTest {
    private static final double DELTA = .001;

    @Before
    public void populateEmployeeRepository() {
        Employee employee = new Employee();
        employee.empId = "1";
        employee.name = "ANY NAME";
        employee.address = "ANY ADDRESS";
        employee.payCalculator = new HourlyRate();
        EmployeeRepositoryInstance.get().save(employee);
        UnionMemberships.cleanAll();
    }

    @Test
    public void should_change_employee_name() throws Exception {
        transaction.execute("ChgEmp <1> Name <New Name>");
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertEquals("New Name", employee.name);
    }

    @Test(expected = EmployeeNotExist.class)
    public void should_throw_ex_when_employee_not_exist() throws Exception {
        assertNull(EmployeeRepositoryInstance.get().getById("999"));
        transaction.execute("ChgEmp <999> Name <New Name>");
    }

    @Test(expected = UnknownChangeAction.class)
    public void should_throw_ex_when_change_action_is_unknown() throws Exception {
        transaction.execute("ChgEmp <1> UNKNOWN");
    }

    @Test
    public void should_change_employee_address() throws Exception {
        transaction.execute("ChgEmp <1> Address <New Address>");
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertEquals("New Address", employee.address);
    }

    @Test
    public void should_change_employee_to_hourly() throws Exception {
        transaction.execute("ChgEmp <1> Hourly <12.5>");
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertEquals(12.5, ((HourlyRate)employee.payCalculator).getRate(), DELTA);
    }

    @Test
    public void should_change_employee_to_salaried() throws Exception {
        transaction.execute("ChgEmp <1> Salaried <1000>");
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertEquals(1000, ((MonthlySalary)employee.payCalculator).getSalary(), DELTA);
    }

    @Test
    public void should_change_employee_to_commissioned() throws Exception {
        transaction.execute("ChgEmp <1> Commissioned <1000> <12.5>");
        Employee employee = EmployeeRepositoryInstance.get().getById("1");
        assertEquals(1000, ((CommissionRate)employee.payCalculator).getSalary(), DELTA);
        assertEquals(12.5, ((CommissionRate)employee.payCalculator).getRate(), DELTA);
    }

    @Test
    public void should_put_employee_in_union() throws Exception {
        assertNull(UnionMemberships.getByEmpId("1"));
        transaction.execute("ChgEmp <1> Member <2> Dues <2.99>");
        UnionMembership unionMembership = UnionMemberships.getByEmpId("1");
        assertNotNull(unionMembership);
        assertEquals("1", unionMembership.empId);
        assertEquals("2", unionMembership.memberId);
        assertEquals(2.99, unionMembership.rate, DELTA);
    }

    @Test(expected = EmployeeAlreadyInUnionMember.class)
    public void should_throw_ex_when_putting_already_assigned_employee_in_union() throws Exception {
        putEmployeeInUnion();
        assertNotNull(UnionMemberships.getByEmpId("1"));
        transaction.execute("ChgEmp <1> Member <1> Dues <2.99>");
    }

    @Test(expected = ChangeActionPartIsMissing.class)
    public void should_throw_ex_when_dues_text_is_missing() throws Exception {
        transaction.execute("ChgEmp <1> Member <1> XXX <2.99>");
    }

    @Test
    public void should_remove_employee_from_union() throws Exception {
        putEmployeeInUnion();
        transaction.execute("ChgEmp <1> NoMember");
        assertNull(UnionMemberships.getByEmpId("1"));
    }

    @Test
    public void should_throw_ex_when_removing_not_assigned_employee_from_union() throws Exception {
        assertNull(UnionMemberships.getByEmpId("1"));
        transaction.execute("ChgEmp <1> NoMember");
        assertNull(UnionMemberships.getByEmpId("1"));
    }

    private void putEmployeeInUnion() {
        UnionMembership um = new UnionMembership();
        um.empId = "1";
        um.memberId = "1";
        UnionMemberships.addMembership(um);
    }
}
