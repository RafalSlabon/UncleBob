package payroll.transaction;

public class EmployeeNotExist extends RuntimeException {
    public EmployeeNotExist(String empId) {
        super("Cannot execute transaction on employee with id=" + empId + " because it not exist!");
    }
}
