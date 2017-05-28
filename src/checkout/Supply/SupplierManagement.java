package checkout.Supply;

import checkout.Product.Product;
import checkout.util.JsonDatabase;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class SupplierManagement
{
    private ArrayList<Supplier> supplierList = null;

    public SupplierManagement(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Load what we want to load...
        // The object type should be set to ArrayList<Product> in order to correctly parse from JSON file.
        this.supplierList = jsonDatabase.readObjectFromFile(fileName, new TypeToken<ArrayList<Supplier>>() {}.getType());

        // In some sceneario the list may be null, so, set a new one.
        if(this.supplierList == null)
        {
            this.supplierList = new ArrayList<>();
        }
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
            supplierList.add(new Supplier(getNewSupplierId(this.supplierList),
                    product, supplierName, supplierEmail, supplierPhone, new Date()));
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

    private int getNewSupplierId(ArrayList<Supplier> userList)
    {
        // Create a supplier list
        ArrayList<Integer> supplierIdList = new ArrayList<>();

        for(Supplier supplierToQuery : userList)
        {
            supplierIdList.add(supplierToQuery.getSupplierId());
        }

        // Sort it in ascend
        Collections.sort(supplierIdList);

        // If the supplier ID list does not have any user in it, return 0 because there no supplier!
        // Otherwise, find the largest supplier ID, then plus one to get the new ID.
        if(supplierIdList.size() == 0)
        {
            return 0;
        }
        else
        {
            return (supplierIdList.get(supplierIdList.size() - 1) + 1);
        }
    }

}
