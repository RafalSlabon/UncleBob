package payroll.transaction;

public class InvalidPayCalculator extends RuntimeException {
    public InvalidPayCalculator(String empId) {
        super("Cannot execute transaction on employee with id=" + empId + " because its pay calculator is invalid!");
    }
}
