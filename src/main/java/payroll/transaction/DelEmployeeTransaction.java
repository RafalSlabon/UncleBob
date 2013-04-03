package payroll.transaction;


import payroll.Employee;
import payroll.repository.EmployeeRepositoryInstance;

public class DelEmployeeTransaction implements Transaction {
    private TransactionParts parts;

    public DelEmployeeTransaction(TransactionParts parts) {
        this.parts = parts;
    }

    @Override
    public void execute() {
        String empId = parts.nextPartWithoutBrackets();
        Employee employee = EmployeeRepositoryInstance.get().getById(empId);
        if (employee == null)
            throw new EmployeeNotExist(empId);

        EmployeeRepositoryInstance.get().delete(empId);
    }
}
