package payroll.transaction;

import payroll.Employee;
import payroll.repository.EmployeeRepositoryInstance;

public class AddEmployeeTransaction implements Transaction {
    private Employee newEmployee;
    private TransactionParts parts;

    public AddEmployeeTransaction(TransactionParts parts) {
        this.parts = parts;
    }

    @Override
    public void execute() {
        createNewEmployee();
        EmployeeRepositoryInstance.get().save(newEmployee);
    }

    private void createNewEmployee() {
        newEmployee = new Employee();
        setEmpId();
        setName();
        setAddress();
        setPayCalculator();
    }

    private void setEmpId() {
        String empId = parts.nextPartWithoutBrackets();
        newEmployee.empId = empId;
    }

    private void setName() {
        String name = parts.nextPartWithoutQuotes();
        newEmployee.name = name;
    }

    private void setAddress() {
        String address = parts.nextPartWithoutQuotes();
        newEmployee.address = address;
    }

    private void setPayCalculator() {
        newEmployee.payCalculator = PayCalculatorFactory.createFrom(parts);
    }
}
