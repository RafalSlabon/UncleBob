package payroll.transaction;

import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.PayCalculator;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {
    public AddHourlyEmployeeTransaction(AddHourlyEmployeeRequest request) {
        super(request);
    }

    @Override
    protected PayCalculator createPayCalculator() {
        HourlyRate hr = new HourlyRate();
        hr.setRate(((AddHourlyEmployeeRequest)request).rate);
        return hr;
    }
}
