package checkout;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
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

    public static void saveProductList(ArrayList<Product> productArrayList) {
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

    public static Product scanProductID() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        String scannedProductID;
        Product product = null;

        while (!validInput) {
            System.out.print("Scan your next item: ");
            scannedProductID = scanner.nextLine();

            try {
                valueOf(scannedProductID);
            } catch (NumberFormatException e) {
                System.out.println("Cannot find product. Please scan again. \n");
                continue;
            }

            product = Product.getProductByID(valueOf(scannedProductID));

            if (product == null) {
                System.out.println("Cannot find product. Please scan again. \n");
                continue;
            }

            validInput = true;
        }
        return product;
    }

    public static void deductQuantity(Product product, int amount) {
        ArrayList<Product> productArrayList = getProductList();

        int index = product.getID()-1;

        product.setQuantity(product.getQuantity() - amount);
        productArrayList.set(index, product);

        saveProductList(productArrayList);
    }

    public static void addPromo(ArrayList<SaleRecordLine> salesRecordArrayList) {
        for (SaleRecordLine saleRecordLine: salesRecordArrayList) {
            if (saleRecordLine.getProduct().getPromoCondition() != 0) {
                if (saleRecordLine.getQuantity() % saleRecordLine.getProduct().getPromoCondition() > 0) {
//                  add promo here

                }
            }
        }
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

