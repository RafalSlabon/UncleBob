package payroll.entity;

import payroll.payCalculator.PayCalculator;
import payroll.union.UnionMembership;

public interface Employee {
    String getEmpId();

    void setEmpId(String empId);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    PayCalculator getPayCalculator();

    void setPayCalculator(PayCalculator payCalculator);

    PayMethod getPayMethod();

    void setPayMethod(PayMethod payMethod);

    Membership getMembership();

    void setMembership(Membership membership);
}
