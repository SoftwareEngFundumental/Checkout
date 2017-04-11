package checkout;

public class CreditCard {
    private int cardNumber;
    private double cardValue;

    public CreditCard() {
        // TODO: 11/04/2017 auto create ID
        cardValue = 0;
        // TODO: 11/04/2017 stor into JSON file
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public double getCardValue() {
        return cardValue;
    }

    public void setCardValue(double cardValue) {
        this.cardValue = cardValue;
    }

    public void rechargeValue() {
        // TODO: 11/04/2017 put money in to the card (maybe just change the cardValue because we can't process real money transaction)
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", cardValue=" + cardValue +
                '}';
    }
}
