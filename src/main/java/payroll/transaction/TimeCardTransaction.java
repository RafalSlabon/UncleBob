package payroll.transaction;

import payroll.Employee;
import payroll.TimeCard;
import payroll.payCalculator.HourlyRate;
import payroll.repository.EmployeeRepositoryInstance;
import payroll.util.DateParser;

public class TimeCardTransaction implements Transaction {
    private TransactionParts parts;

    public TimeCardTransaction(TransactionParts parts) {
        this.parts = parts;
    }

    @Override
    public void execute() throws Exception {
        String empId = parts.nextPartWithoutBrackets();
        String date = parts.nextPartWithoutBrackets();
        String hours = parts.nextPartWithoutBrackets();

        Employee employee = EmployeeRepositoryInstance.get().getById(empId);
        if (employee == null)
            throw new EmployeeNotExist(empId);

        if (!(employee.payCalculator instanceof HourlyRate))
            throw new InvalidPayCalculator(empId);

        HourlyRate hr = (HourlyRate) employee.payCalculator;
        TimeCard timeCard = new TimeCard();
        timeCard.date = DateParser.parseDateFromText(date);
        timeCard.hours = Double.parseDouble(hours);
        hr.addTimeCard(timeCard);
    }
}
