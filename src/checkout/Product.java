package checkout;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Integer.valueOf;

public class Product {
    private int ID;
    private String name;
    private double price;
    private int quantity;
    private int promoCondition;
    private double promoDiscount;

    public Product(String name, double price, int quantity) {
        this.ID = getProductList().size() + 1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }



    public static ArrayList<Product> getProductList() {
        Type productListType = new TypeToken<ArrayList<Product>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Product> productArrayList = jsonDatabase.readObjectFromFile("productList.json", productListType);
        return productArrayList;
    }

    public void saveProductList(ArrayList<Product> productArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(productArrayList, "productList.json");
    }

    public static Product getProductByID(int ID) {
        ArrayList<Product> productArrayList = getProductList();
        Product product = null;
        for (int i = 0; i < productArrayList.size(); i++) {
            Product temp = productArrayList.get(i);
            if (temp.getID() == ID) {
                product = temp;
            }
        }
        return product;
    }



    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPromoCondition() {
        return promoCondition;
    }

    public double getPromoDiscount() {
        return promoDiscount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPromoCondition(int promoCondition) {
        this.promoCondition = promoCondition;
    }

    public void setPromoDiscount(double promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", promoCondition=" + promoCondition +
                ", promoDiscount=" + promoDiscount +
                '}';
    }
}

