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

    public Product(int ID, String name, double price, int quantity) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        this.ID = ID;
        this.name = name;
        this.price = price;
        // TODO: 11/04/2017 store into JSON file 
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

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return ID + "-" + name + "-" + price;
    }
}

