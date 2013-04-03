package payroll.transaction;

import payroll.Employee;
import payroll.payCalculator.CommissionRate;
import payroll.payCalculator.HourlyRate;
import payroll.payCalculator.MonthlySalary;
import payroll.repository.EmployeeRepositoryInstance;
import payroll.union.UnionMembership;
import payroll.union.UnionMemberships;

public class ChangeEmployeeTransaction implements Transaction {
    private TransactionParts parts;
    private Employee employee;

    public ChangeEmployeeTransaction(TransactionParts parts) {
        this.parts = parts;
    }

    @Override
    public void execute() throws Exception {
        String empId = parts.nextPartWithoutBrackets();
        String action = parts.nextPart();

        employee = EmployeeRepositoryInstance.get().getById(empId);
        if(employee == null)
            throw new EmployeeNotExist(empId);

        if (action.equalsIgnoreCase("Name")) {
            changeName();
        } else if (action.equalsIgnoreCase("Address")) {
            changeAddress();
        } else if (action.equalsIgnoreCase("Hourly")) {
            changeToHourly();
        } else if (action.equalsIgnoreCase("Salaried")) {
            changeToMonthlySalary();
        } else if (action.equalsIgnoreCase("Commissioned")) {
            changeToCommissioned();
        } else if (action.equalsIgnoreCase("Member")) {
            changeUnionMembership(empId);
        } else if (action.equalsIgnoreCase("NoMember")) {
            removeFromUnion(empId);
        } else {
            throw new UnknownChangeAction(action);
        }

        EmployeeRepositoryInstance.get().save(employee);
    }

    private void removeFromUnion(String empId) {
        UnionMemberships.removeByEmpId(empId);
    }

    private void changeUnionMembership(String empId) {
        checkIfIsAlreadyUnionMember(empId);
        String memberId = parts.nextPartWithoutBrackets();
        validateIfHasDuesText();
        UnionMemberships.addMembership(createUnionMembership(empId, memberId));
    }

    private void checkIfIsAlreadyUnionMember(String empId) {
        UnionMembership um = UnionMemberships.getByEmpId(empId);
        if (um != null)
            throw new EmployeeAlreadyInUnionMember(empId);
    }

    private UnionMembership createUnionMembership(String empId, String memberId) {
        UnionMembership um = new UnionMembership();
        um.empId = empId;
        um.memberId = memberId;
        String rate = parts.nextPartWithoutBrackets();
        um.rate = Double.parseDouble(rate);
        return um;
    }

    private void validateIfHasDuesText() {
        String text = parts.nextPart();
        if (!text.equalsIgnoreCase("Dues"))
            throw new ChangeActionPartIsMissing("Dues");
    }

    private void changeToCommissioned() {
        CommissionRate cr = new CommissionRate();
        String salary = parts.nextPartWithoutBrackets();
        String rate = parts.nextPartWithoutBrackets();
        cr.setSalary(Double.parseDouble(salary));
        cr.setRate(Double.parseDouble(rate));
        employee.payCalculator = cr;
    }

    private void changeToMonthlySalary() {
        MonthlySalary ms = new MonthlySalary();
        String salary = parts.nextPartWithoutBrackets();
        ms.setSalary(Double.parseDouble(salary));
        employee.payCalculator = ms;
    }

    private void changeToHourly() {
        HourlyRate hr = new HourlyRate();
        String rate = parts.nextPartWithoutBrackets();
        hr.setRate(Double.parseDouble(rate));
        employee.payCalculator = hr;
    }

    private void changeAddress() {
        employee.address = parts.nextPartWithoutBrackets();
    }

    private void changeName() {
        employee.name = parts.nextPartWithoutBrackets();
    }

    public static class ChangeActionPartIsMissing extends RuntimeException {
        public ChangeActionPartIsMissing(String missingPart) {
            super("Missing change employee action part: " + missingPart);
        }
    }

    public static class UnknownChangeAction extends RuntimeException {
        public UnknownChangeAction(String action) {
            super("Unknown change employee action!: '" + action + "'");
        }
    }

    public static class EmployeeAlreadyInUnionMember extends RuntimeException {
        public EmployeeAlreadyInUnionMember(String empId) {
            super("Employee with empId=" + empId + " is already union member");
        }
    }
}
