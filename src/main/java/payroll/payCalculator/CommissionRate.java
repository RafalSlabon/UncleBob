package payroll.payCalculator;


import payroll.SalesReceipt;

import java.util.ArrayList;
import java.util.List;

public class CommissionRate implements PayCalculator {
    private double salary;
    private double rate;
    private List<SalesReceipt> saleReceipts = new ArrayList<SalesReceipt>();

    @Override
    public double calculatePay() {
        return 0;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSalary() {
        return salary;
    }

    public double getRate() {
        return rate;
    }

    public void addSaleReceipt(SalesReceipt salesReceipt) {
        saleReceipts.add(salesReceipt);
    }

    public List<SalesReceipt> getSaleReceipts() {
        return saleReceipts;
    }
}
