package checkout;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreditCard {
    private int cardNumber;
    private double cardValue;

    public CreditCard(double initialValue) {
        cardNumber = getCreditCardList().size()+1;
        cardValue = initialValue;
    }


    public static ArrayList<CreditCard> getCreditCardList() {
        Type creditCardListType = new TypeToken<ArrayList<CreditCard>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<CreditCard> creditCardsArrayList = jsonDatabase.readObjectFromFile("creditCardList.json", creditCardListType);
        return creditCardsArrayList;
    }

    public static void saveCreditCardList(ArrayList<CreditCard> creditCardsArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(creditCardsArrayList, "creditCardList.json");
    }

    public static CreditCard getCreditCardByID(int ID) {
        ArrayList<CreditCard> creditCardArrayList = getCreditCardList();
        CreditCard creditCard = null;
        for (int i = 0; i < creditCardArrayList.size(); i++) {
            CreditCard temp = creditCardArrayList.get(i);
            if (temp.getCardNumber() == ID) {
                creditCard = temp;
            }
        }
        return creditCard;
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
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", cardValue=" + cardValue +
                '}';
    }
}
