public class CreditCard {
    private String cardNumber;
    private String firstName;
    private String lastName;
    private String CVV;
    private int cardExpireYear;
    private int cardExpireMonth;

    public CreditCard(String cardNumber, String firstName, String lastName, String CVV, int cardExpireYear, int cardExpireMonth) {
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CVV = CVV;
        this.cardExpireYear = cardExpireYear;
        this.cardExpireMonth = cardExpireMonth;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public int getCardExpireYear() {
        return cardExpireYear;
    }

    public void setCardExpireYear(int cardExpireYear) {
        this.cardExpireYear = cardExpireYear;
    }

    public int getCardExpireMonth() {
        return cardExpireMonth;
    }

    public void setCardExpireMonth(int cardExpireMonth) {
        this.cardExpireMonth = cardExpireMonth;
    }
}
