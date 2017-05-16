package checkout.Product;

import java.lang.reflect.Type;
import java.util.*;
import java.io.*;

import checkout.SalesRecord.SalesRecordLine;
import checkout.util.*;
import com.google.gson.reflect.TypeToken;

public class ProductManagement
{
    private ArrayList<Product> productList;

    public ProductManagement(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Load what we want to load...
        // The object type should be set to ArrayList<Product> in order to correctly parse from JSON file.
        this.productList = jsonDatabase.readObjectFromFile(fileName, new TypeToken<ArrayList<Product>>() {}.getType());

    }

    public ProductManagement()
    {
        this.productList = new ArrayList<>();
    }

    public void setProductList(ArrayList<Product> productList) { this.productList = productList; }

    public ArrayList<Product> getProductList() { return this.productList; }

    public void loadFromFile(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Load what we want to load...
        // The object type should be set to ArrayList<Product> in order to correctly parse from JSON file.
        this.productList = jsonDatabase.readObjectFromFile(fileName, new TypeToken<ArrayList<Product>>() {}.getType());
    }

    public void saveToFile(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Save what we want to save...
        jsonDatabase.saveObjectToJsonFile(this.productList, fileName);
    }

    public void addProduct(Product product)
    {
        if(searchProduct(product.getID()) == null || searchProduct(product.getName()) == null)
        {
            productList.add(product);
        }
    }

    public boolean addProduct(String productName, double productPrice, int productQuantity)
    {
        Product product = new Product(productName, productPrice, productQuantity);

        if(searchProduct(product.getID()) == null || searchProduct(product.getName()) == null)
        {
            productList.add(product);
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean addProduct(String productName, double productPrice)
    {
        Product product = new Product(productName, productPrice);

        if(searchProduct(product.getID()) == null || searchProduct(product.getName()) == null)
        {
            productList.add(product);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeProduct(String productName)
    {
        Product product = searchProduct(productName);

        if(product != null)
        {
            return productList.remove(product);
        }
        else
        {
            return false;
        }
    }

    public boolean removeProduct(int productId)
    {
        Product product = searchProduct(productId);

        if(product != null)
        {
            return productList.remove(product);
        }
        else
        {
            return false;
        }
    }

    public boolean modifyProduct(Product product,
                                 int productId, String productName, double productPrice,
                                 int productQuantity, boolean prodductHasPromo)
    {
        if(product == null)
        {
            return false;
        }

        product.setName(productName);
        product.setHasPromo(prodductHasPromo);
        product.setPrice(productPrice);
        product.setQuantity(productQuantity);

        return true;
    }

    public Product searchProduct(int productId)
    {
        for(Product product : this.productList)
        {
            // If match, return this Product object immediately and stop the procedure
            if(product.getID() == productId)
            {
                return product;
            }
        }

        return null;
    }

    public Product searchProduct(String productName)
    {
        for(Product product : this.productList)
        {
            // If match, return this Product object immediately and stop the procedure
            if(product.getName().equals(productName))
            {
                return product;
            }
        }

        return null;
    }

}
