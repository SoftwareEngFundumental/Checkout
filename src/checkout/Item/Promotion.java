package checkout.Item;

import checkout.JsonDatabase;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Promotion extends Item{
    private int condition;
    private Product appliedProduct;
    // TODO: 04/05/2017 Promo: change constructor: input discountPercentage, self calculate


    public Promotion(String name, double price, Product appliedProduct, int condition) {
        super(name, price);
        this.appliedProduct = appliedProduct;
        this.condition = condition;
    }


    public static ArrayList<Promotion> getPromoList() {
        Type promoListType = new TypeToken<ArrayList<Promotion>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        return jsonDatabase.readObjectFromFile("promoList.json", promoListType);
    }

    public static void savePromoList(ArrayList<Promotion> promoArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(promoArrayList, "promoList.json");
    }

    public static Promotion getPromoByProduct(Product product) {
        ArrayList<Promotion> promoArrayList = getPromoList();
        for (int i = 0; i < promoArrayList.size(); i++) {
            Promotion promotion = promoArrayList.get(i);
            if (promotion.getAppliedProduct().getID() == product.getID()) {
                return promotion;
            }
        }
        return null;
    }


    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public Product getAppliedProduct() {
        return appliedProduct;
    }

    public void setAppliedProduct(Product appliedProduct) {
        this.appliedProduct = appliedProduct;
    }


    @Override
    public String toString() {
        return "Promotion{" +
                "Name=" + super.getName() +
                ", Price=" + super.getPrice() +
                ", condition=" + condition +
                ", appliedProduct=" + appliedProduct +
                '}';
    }
}

