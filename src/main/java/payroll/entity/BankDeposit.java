package payroll.entity;


public class BankDeposit implements PayMethod {
    private String bank;
    private String accountNumber;

    public BankDeposit(String bank, String accountNumber) {
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void deliverPayment() {
        System.out.println("Deposit payment to bank: " + bank + " and account: " + accountNumber);
    }
}
