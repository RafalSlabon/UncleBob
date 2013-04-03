package payroll.transaction;

public class TransactionExecutor {

    public void executeAndPrintError(String text) {
        try {
            execute(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void execute(String text) throws Exception {
        TransactionParts parts = new TransactionParts(text);
        String transactionName = parts.nextPart();

        Transaction transaction = null;
        if (transactionName.equalsIgnoreCase("AddEmp")) {
            transaction = new AddEmployeeTransaction(parts);
        } else if (transactionName.equalsIgnoreCase("DelEmp")) {
            transaction = new DelEmployeeTransaction(parts);
        } else if (transactionName.equalsIgnoreCase("TimeCard")) {
            transaction = new TimeCardTransaction(parts);
        } else if (transactionName.equalsIgnoreCase("SaleReceipt")) {
            transaction = new SalesReceiptTransaction(parts);
        } else if (transactionName.equalsIgnoreCase("ServiceCharge")) {
            transaction = new ServiceChargeTransaction(parts);
        } else if (transactionName.equalsIgnoreCase("ChgEmp")) {
            transaction = new ChangeEmployeeTransaction(parts);
        } else {
            throw new InvalidTransactionName(transactionName);
        }
        transaction.execute();
    }

    public static class InvalidTransactionName extends RuntimeException {
        public InvalidTransactionName(String name) {
            super("Invalid transaction name!: " + name);
        }
    }
}
