package payroll.transaction;

import payroll.Employee;
import payroll.SalesReceipt;
import payroll.payCalculator.CommissionRate;
import payroll.repository.EmployeeRepositoryInstance;
import payroll.util.DateParser;

public class SalesReceiptTransaction implements Transaction {
    private TransactionParts parts;

    public SalesReceiptTransaction(TransactionParts parts) {
        this.parts = parts;
    }

    @Override
    public void execute() throws Exception {
        String empId = parts.nextPartWithoutBrackets();
        String date = parts.nextPartWithoutBrackets();
        String amount = parts.nextPartWithoutBrackets();

        Employee employee = EmployeeRepositoryInstance.get().getById(empId);
        if (employee == null)
            throw new EmployeeNotExist(empId);
        if (!(employee.payCalculator instanceof CommissionRate))
            throw new InvalidPayCalculator(empId);

        CommissionRate cr = (CommissionRate) employee.payCalculator;
        SalesReceipt salesReceipt = new SalesReceipt();
        salesReceipt.date = DateParser.parseDateFromText(date);
        salesReceipt.amount = Integer.parseInt(amount);
        cr.addSaleReceipt(salesReceipt);
    }
}
