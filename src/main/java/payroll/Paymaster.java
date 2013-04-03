package payroll;

public class Paymaster implements PayMethod {
    @Override
    public void deliverPayment() {
        System.out.println("Held by the Paymaster");
    }
}
