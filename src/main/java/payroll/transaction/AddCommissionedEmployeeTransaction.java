package payroll.transaction;

import payroll.payCalculator.CommissionRate;
import payroll.payCalculator.PayCalculator;


public class AddCommissionedEmployeeTransaction extends AddEmployeeTransaction {
    public AddCommissionedEmployeeTransaction(AddCommissionedEmployeeRequest request) {
        super(request);
    }

    @Override
    protected PayCalculator createPayCalculator() {
        CommissionRate cr = new CommissionRate();
        cr.setSalary(((AddCommissionedEmployeeRequest) request).salary);
        cr.setRate(((AddCommissionedEmployeeRequest) request).rate);
        return cr;
    }
}
