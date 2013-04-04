package payroll.transaction;

import payroll.entity.Employee;
import payroll.payCalculator.PayCalculator;
import payroll.repository.EmployeeImpl;
import payroll.repository.EmployeeRepositoryInstance;

public abstract class AddEmployeeTransaction implements Transaction {
    private Employee newEmployee;
    protected final AddEmployeeRequest request;


    public AddEmployeeTransaction(AddEmployeeRequest request) {
        this.request = request;
    }

    @Override
    public void execute() {
        createNewEmployee();
        EmployeeRepositoryInstance.get().save(newEmployee);
    }

    private void createNewEmployee() {
        newEmployee = new EmployeeImpl();
        newEmployee.setEmpId(request.empId);
        newEmployee.setName(request.name);
        newEmployee.setAddress(request.address);
        newEmployee.setPayCalculator(createPayCalculator());
    }

    abstract protected PayCalculator createPayCalculator();
}
