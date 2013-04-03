package payroll.transaction;


public class TransactionParts {
    public static final int BEGINNING = 0;
    public static final int NOT_FOUND = -1;
    private String transactionText;

    public TransactionParts(String transactionText) {
        this.transactionText = transactionText;
    }

    public String nextPart() {
        int startIndex = 0;
        int endIndex = transactionText.indexOf(" ");
        if (endIndex == -1)
            endIndex = transactionText.length();

        return nextPart(startIndex, endIndex, 0);
    }


    public String nextPartWithoutBrackets() {
        int startIndex = transactionText.indexOf("<");
        int endIndex = transactionText.indexOf(">");
        return nextPart(startIndex, endIndex, 1);
    }

    public String nextPartWithoutQuotes() {
        int startIndex = transactionText.indexOf("\"");
        int endIndex = transactionText.indexOf("\"", startIndex + 1);
        return nextPart(startIndex, endIndex, 1);
    }

    private String nextPart(int startIndex, int endIndex, int offset) {
        validateParts(startIndex, endIndex);
        String part = transactionText.substring(startIndex + offset, endIndex);
        transactionText = transactionText.substring(endIndex + offset, transactionText.length());
        transactionText = transactionText.trim();
        return part;
    }

    private void validateParts(int startIndex, int endIndex) {
        if (transactionText.isEmpty())
            throw new NotEnoughParts();
        if (startIndex != BEGINNING || endIndex == NOT_FOUND)
            throw new InvalidValue();
    }

    public static class InvalidValue extends RuntimeException {
        public InvalidValue() {
            super("Invalid transaction value!");
        }
    }

    public static class NotEnoughParts extends RuntimeException {
        public NotEnoughParts() {
            super("Invalid number of transaction parts!");
        }
    }
}
