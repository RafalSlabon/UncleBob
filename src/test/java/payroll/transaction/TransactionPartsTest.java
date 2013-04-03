package payroll.transaction;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static payroll.transaction.TransactionParts.InvalidValue;
import static payroll.transaction.TransactionParts.NotEnoughParts;

public class TransactionPartsTest {

    @Test
    public void should_get_all_transaction_parts(){
        TransactionParts transactionParts = new TransactionParts("ChgEmp <1> Name \"New Name\" X");
        assertEquals("ChgEmp", transactionParts.nextPart());
        assertEquals("1", transactionParts.nextPartWithoutBrackets());
        assertEquals("Name", transactionParts.nextPart());
        assertEquals("New Name", transactionParts.nextPartWithoutQuotes());
        assertEquals("X", transactionParts.nextPart());
    }

    @Test(expected = NotEnoughParts.class)
    public void should_get_not_enough_parts(){
        TransactionParts transactionParts = new TransactionParts("");
        transactionParts.nextPart();
    }

    @Test(expected = InvalidValue.class)
    public void should_get_invalid_value(){
        TransactionParts transactionParts = new TransactionParts("<XXX%");
        transactionParts.nextPartWithoutBrackets();
    }
}
