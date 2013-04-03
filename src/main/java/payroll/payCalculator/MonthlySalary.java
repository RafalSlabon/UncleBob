package payroll.payCalculator;


public class MonthlySalary implements PayCalculator {
    private double salary;

    @Override
    public double calculatePay() {
        return 0;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
