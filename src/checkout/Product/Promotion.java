package checkout.Product;

import checkout.util.JsonDatabase;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Promotion {
    private String name;
    private double price;
    private int condition;
    private Product appliedProduct;

    public Promotion(String name, double discountAmount, Product appliedProduct, int condition) {
        this.name = name;
        this.price = appliedProduct.getPrice()*discountAmount;
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

    public void savePromoInfoToList() {
        ArrayList<Promotion> promotionArrayList = getPromoList();
        for (Promotion promotionInList:promotionArrayList) {
            if (promotionInList.equals(this)) {
                int index = promotionArrayList.indexOf(promotionInList);
                promotionArrayList.set(index,this);
            }
        }
        Promotion.savePromoList(promotionArrayList);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                "Name=" + name +
                ", Price=" + price +
                ", condition=" + condition +
                ", appliedProduct=" + appliedProduct +
                '}';
    }
}

