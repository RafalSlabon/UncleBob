package payroll.transaction;

import payroll.payCalculator.CommissionRate;
import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.MonthlySalary;
import payroll.payCalculator.PayCalculator;

public class PayCalculatorFactory {

    public static PayCalculator createFrom(TransactionParts parts) {
        String payCalculatorType = parts.nextPart();
        if (isHourlyRate(payCalculatorType)) {
            return createHourlyRate(parts);
        } else if (isMonthlySalary(payCalculatorType)) {
            return createMonthlySalary(parts);
        } else if (isCommissionRate(payCalculatorType)) {
            return createCommissionRate(parts);
        }

        throw new IllegalArgumentException("Invalid payCalculatorType!");
    }

    private static boolean isCommissionRate(String payCalculatorType) {
        return payCalculatorType.equals("C");
    }

    private static boolean isMonthlySalary(String payCalculatorType) {
        return payCalculatorType.equals("S");
    }

    private static boolean isHourlyRate(String payCalculatorType) {
        return payCalculatorType.equals("H");
    }

    private static PayCalculator createCommissionRate(TransactionParts parts) {
        CommissionRate commissionRate = new CommissionRate();
        String salary = parts.nextPartWithoutBrackets();
        commissionRate.setSalary(Double.parseDouble(salary));
        String rate = parts.nextPartWithoutBrackets();
        commissionRate.setRate(Double.parseDouble(rate));
        return commissionRate;
    }

    private static PayCalculator createMonthlySalary(TransactionParts parts) {
        MonthlySalary monthlySalary = new MonthlySalary();
        String salary = parts.nextPartWithoutBrackets();
        monthlySalary.setSalary(Double.parseDouble(salary));
        return monthlySalary;
    }

    private static PayCalculator createHourlyRate(TransactionParts parts) {
        HourlyRate hourlyRate = new HourlyRate();
        String rate = parts.nextPartWithoutBrackets();
        hourlyRate.setRate(Double.parseDouble(rate));
        return hourlyRate;
    }
}
