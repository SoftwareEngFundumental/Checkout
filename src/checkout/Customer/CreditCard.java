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

    public void saveCreditCardInfoToList() {
        ArrayList<CreditCard> creditCardArrayList = getCreditCardList();
        for (CreditCard creditCardInList:creditCardArrayList) {
            if (creditCardArrayList.equals(this)) {
                int index = creditCardArrayList.indexOf(creditCardInList);
                creditCardArrayList.set(index,this);
            }
        }
        CreditCard.saveCreditCardList(creditCardArrayList);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        return cardNumber == that.cardNumber;

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
