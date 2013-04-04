package payroll.entity;

/**
 * Created with IntelliJ IDEA.
 * User: radagast
 * Date: 4/3/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Postal implements PayMethod {
    private String postAddress;

    public Postal(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostAddress() {
        return postAddress;
    }

    @Override
    public void deliverPayment() {
        System.out.println("Posting payment to <" + postAddress + ">");
    }
}
