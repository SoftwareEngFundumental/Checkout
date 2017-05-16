package checkout.Product;

import checkout.util.JsonDatabase;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Product {
    private int ID;
    private String name;
    private double price;
    private int quantity;
    private boolean hasPromo;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        int newProductID = 0;
        boolean isAvailableForNewProduct = false;
        do {
            newProductID++;
            if (getProductByID(newProductID) == null) {
                isAvailableForNewProduct = true;
            }
        } while (!isAvailableForNewProduct);
        this.ID = newProductID;
        this.quantity = quantity;
        this.hasPromo = false;
    }

    public Product(String name, double price) {
        this(name, price, 0);
    }


    public static ArrayList<Product> getProductList() {
        Type productListType = new TypeToken<ArrayList<Product>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        return jsonDatabase.readObjectFromFile("productList.json", productListType);
    }

    public static void saveProductList(ArrayList<Product> productArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(productArrayList, "productList.json");
    }

    public void saveProductInfoToList() {
        ArrayList<Product> productArrayList = getProductList();
        for (Product productInList : productArrayList) {
            if (productInList.equals(this)) {
                int index = productArrayList.indexOf(productInList);
                productArrayList.set(index,this);
            }
        }
        Product.saveProductList(productArrayList);
    }

    public static Product getProductByID(int ID) {
        ArrayList<Product> productArrayList = getProductList();
        for (int i = 0; i < productArrayList.size(); i++) {
            Product product = productArrayList.get(i);
            if (product.getID() == ID) {
                return  product;
            }
        }
        return null;
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


    public int getID() {
        return ID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return ID == product.ID;

    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name +
                ", price=" + price +
                ", quantity=" + quantity +
                ", hasPromo=" + hasPromo +
                '}';
    }
}

