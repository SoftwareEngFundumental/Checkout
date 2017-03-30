public class Customer {
    private String customerName;
    private CreditCard creditCard;
    private int point;

    public Customer(String customerName, CreditCard creditCard) {
        this.customerName = customerName;
        this.creditCard = creditCard;
        this.point = 0;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
