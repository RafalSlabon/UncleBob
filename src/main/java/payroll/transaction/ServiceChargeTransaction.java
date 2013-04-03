package payroll.transaction;

import payroll.union.UnionMembership;
import payroll.union.UnionMemberships;

public class ServiceChargeTransaction implements Transaction {
    private TransactionParts parts;

    public ServiceChargeTransaction(TransactionParts parts) {
        this.parts = parts;
    }

    @Override
    public void execute() throws Exception {
        String memberId = parts.nextPartWithoutBrackets();
        String rate = parts.nextPartWithoutBrackets();

        UnionMembership um = UnionMemberships.getByMemberId(memberId);
        if (um == null)
            throw new InvalidUnionMembership(memberId);

        um.rate = Double.parseDouble(rate);
    }

    public static class InvalidUnionMembership extends RuntimeException {
        public InvalidUnionMembership(String memberId) {
            super("Invalid union membership id=" + memberId + " !");
        }
    }
}
