package checkout.Supply;

import checkout.Product.Product;
import checkout.util.JsonDatabase;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;

public class SupplierManagement
{
    private ArrayList<Supplier> supplierList;

    public SupplierManagement(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Load what we want to load...
        // The object type should be set to ArrayList<Product> in order to correctly parse from JSON file.
        this.supplierList = jsonDatabase.readObjectFromFile(fileName, new TypeToken<ArrayList<Supplier>>() {}.getType());
    }

    public SupplierManagement()
    {
        this.supplierList = new ArrayList<>();
    }

    public ArrayList<Supplier> getRawSupplierList()
    {
        return supplierList;
    }

    public void setRawSupplierList(ArrayList<Supplier> supplierList)
    {
        this.supplierList = supplierList;
    }

    public boolean addSupplierToList(Product product, String supplierName, String supplierEmail, String supplierPhone)
    {
        if(product != null && !supplierName.equals(""))
        {
            supplierList.add(new Supplier(product, supplierName, supplierEmail, supplierPhone, new Date()));
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeSupplierFromList(Supplier supplier)
    {
        if(supplier != null)
        {
            supplierList.remove(supplier);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Supplier findSupplier(Product product)
    {
        if(product == null)
        {
            return null;
        }

        for(Supplier currentSupplier : this.supplierList)
        {
            // It's safer to validate both product name and ID
            if(currentSupplier.getProduct().getID() == product.getID()
                    && currentSupplier.getProduct().getName().equals(product.getName()))
            {
                return currentSupplier;
            }
        }

        return null;
    }


    public Supplier findSupplier(int id)
    {
        for(Supplier currentSupplier : this.supplierList)
        {
            if(currentSupplier.getSupplierId() == id)
            {
                return currentSupplier;
            }
        }

        return null;
    }

    public double getSupplierTotalPrice(Supplier supplier)
    {
        return supplier.getProduct().getPrice() * supplier.getProduct().getQuantity();
    }

    public void saveSupplierList(String filePath)
    {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(this.supplierList, filePath);
    }

}
