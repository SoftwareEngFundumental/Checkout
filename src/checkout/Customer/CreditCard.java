package checkout.Customer;

import checkout.JsonDatabase;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreditCard {
    private int cardNumber;
    private double cardValue;

    public CreditCard(double initialValue) {
        int newCardNumber = 0;
        boolean isAvailableForNewCard = false;
        do {
            newCardNumber++;
            if (getCardByNumber(newCardNumber) == null) {
                isAvailableForNewCard = true;
            }
        }while (!isAvailableForNewCard);
        cardNumber = newCardNumber;
        cardValue = initialValue;
    }


    public static ArrayList<CreditCard> getCreditCardList() {
        Type creditCardListType = new TypeToken<ArrayList<CreditCard>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        return jsonDatabase.readObjectFromFile("creditCardList.json", creditCardListType);
    }

    public static void saveCreditCardList(ArrayList<CreditCard> creditCardsArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(creditCardsArrayList, "creditCardList.json");
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

    public void rechargeValue(int amount) {
        setCardValue(getCardValue() + amount);
    }

    public CreditCard getCardByNumber(int cardNumber) {
        ArrayList <CreditCard> creditCardArrayList = getCreditCardList();
        for (int i = 0; i < creditCardArrayList.size(); i++) {
            CreditCard creditCard = creditCardArrayList.get(i);
            if (creditCard.getCardNumber() == cardNumber) {
                return creditCard;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", cardValue=" + cardValue +
                '}';
    }
}
