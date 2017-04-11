package checkout;

public class Customer
{
    private int ID;
    private String name;
    private int cardNumber;
    private int point;

    public Customer(String name, int cardNumber)
    {
        // TODO: 11/04/2017 auto create ID
        this.name = name;
        this.cardNumber = cardNumber;
        this.point = 0;
        // TODO: 11/04/2017 store into JSON file
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return "Customer{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", cardNumber=" + cardNumber +
                ", point=" + point +
                '}';
    }
}
