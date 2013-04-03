package payroll.transaction;

import org.junit.Before;
import org.junit.Test;
import payroll.union.UnionMembership;
import payroll.union.UnionMemberships;

import static junit.framework.Assert.assertEquals;
import static payroll.transaction.ServiceChargeTransaction.InvalidUnionMembership;

public class ServiceTransactionTest extends TransactionTest {
    private static final double DELTA = .001;

    @Before
    public void cleanMemberships() {
        UnionMemberships.cleanAll();
    }

    @Test
    public void should_charge_union_member() throws Exception {
        UnionMembership unionMembership = new UnionMembership();
        unionMembership.memberId = "1";
        UnionMemberships.addMembership(unionMembership);

        transaction.execute("ServiceCharge <1> <12.5>");
        UnionMembership serviceCharged = UnionMemberships.getByMemberId("1");
        assertEquals(12.5, serviceCharged.rate, DELTA);
    }

    @Test(expected = InvalidUnionMembership.class)
    public void should_not_charge_not_existing_union_member() throws Exception {
        transaction.execute("ServiceCharge <1> <12.5>");
    }
}
