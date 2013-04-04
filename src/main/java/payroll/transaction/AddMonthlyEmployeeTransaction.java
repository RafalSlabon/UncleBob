package payroll.transaction;

import payroll.payCalculator.MonthlySalary;
import payroll.payCalculator.PayCalculator;

public class AddMonthlyEmployeeTransaction extends AddEmployeeTransaction {
    public AddMonthlyEmployeeTransaction(AddMonthlyEmployeeRequest request) {
        super(request);
    }


    @Override
    protected PayCalculator createPayCalculator() {
        MonthlySalary ms = new MonthlySalary();
        ms.setSalary(((AddMonthlyEmployeeRequest)request).salary);
        return ms;
    }
}
