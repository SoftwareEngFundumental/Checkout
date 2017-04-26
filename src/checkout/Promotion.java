package checkout;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Promotion extends Item{
    private int ID;
    private int condition;
    private Product appliedProduct;
    private  boolean active;

    public Promotion(String name, double price, Product appliedProduct, int condition) {
        super(name, price);
        this.ID = 0;
        this.appliedProduct = appliedProduct;
        this.condition = condition;
        this.active = true;
    }


    public static ArrayList<Promotion> getPromoList() {
        Type promoListType = new TypeToken<ArrayList<Promotion>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Promotion> productArrayList = jsonDatabase.readObjectFromFile("promoList.json", promoListType);
        return productArrayList;
    }

    public static void savePromoList(ArrayList<Promotion> promoArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(promoArrayList, "promoList.json");
    }

    public static Promotion getPromoByID(int ID) {
        ArrayList<Promotion> promoArrayList = getPromoList();
        Promotion promotion = null;
        for (int i = 0; i < promoArrayList.size(); i++) {
            Promotion temp = promoArrayList.get(i);
            if (temp.getID() == ID) {
                promotion = temp;
            }
        }
        return promotion;
    }


    public int getID() {
        return ID;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "ID=" + ID +
                ", Name=" + super.getName() +
                ", Price=" + super.getPrice() +
                ", condition=" + condition +
                ", appliedProduct=" + appliedProduct +
                ", active=" + active +
                '}';
    }
}

