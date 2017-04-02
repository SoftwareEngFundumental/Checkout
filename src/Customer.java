public class Customer  extends User
{
    private CreditCard creditCard;
    private int point;

    public Customer(String customerName, CreditCard creditCard)
    {
        super(customerName);
        this.creditCard = creditCard;
        this.point = 0;
    }


    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
