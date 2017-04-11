package checkout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Integer.valueOf;

public class Product {
    private int ID;
    private String name;
    private double price;

    public Product(int ID, String name, double price) {
        JsonDatabase jsonDatabase = new JsonDatabase();

        this.ID = ID;
        this.name = name;
        this.price = price;

        // TODO: 11/04/2017 store into JSON file 
    }

    public int getID () {
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


    // TODO: 11/04/2017 read from JSON instead of TXT
    public static TreeMap<Integer, Product> getProductList(){
        TreeMap<Integer, Product> productTreMap = new TreeMap<Integer, Product>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader("ProductList.txt"));
            String line = br.readLine();
            while (line!=null) {
                String[] parts = line.split("-");
                Integer id = valueOf(parts[0]);
                String name = parts[1];
                Double price = Double.parseDouble(parts[2]);
                productTreMap.put(id, new Product(id+1, name, price));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productTreMap;
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return ID + "-" + name + "-" + price;
    }
}

