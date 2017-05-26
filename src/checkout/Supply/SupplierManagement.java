package checkout.Supply;

import checkout.Product.Product;
import checkout.util.JsonDatabase;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

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


}
