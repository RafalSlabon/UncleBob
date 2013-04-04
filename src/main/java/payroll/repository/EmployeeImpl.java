package payroll.repository;

import payroll.entity.Employee;
import payroll.entity.Membership;
import payroll.entity.PayMethod;
import payroll.payCalculator.PayCalculator;

public class EmployeeImpl implements Employee {
    private String empId;
    private String name;
    private String address;
    private PayCalculator payCalculator;
    private PayMethod payMethod;
    private Membership membership;

    @Override
    public String getEmpId() {
        return empId;
    }

    @Override
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public PayCalculator getPayCalculator() {
        return payCalculator;
    }

    @Override
    public void setPayCalculator(PayCalculator payCalculator) {
        this.payCalculator = payCalculator;
    }

    @Override
    public PayMethod getPayMethod() {
        return payMethod;
    }

    @Override
    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public Membership getMembership() {
        return membership;
    }

    @Override
    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
