package test;

import checkout.Product.Product;
import checkout.Supply.SupplierManagement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */

public class SupplierManagementTest
{
    private SupplierManagement supplierManagement;

    @Before
    public void setUp() throws Exception
    {
        supplierManagement = new SupplierManagement("supplierManagementTest.json");
    }

    @Test
    public void addSupplierToList() throws Exception
    {
        supplierManagement.addSupplierToList(new Product("phone", 5 ,10),
                "DesignedAndMadeInChina", "somewhereInChina@lol.cn", "+8613800138000");

        // Scenario #1 The added item is added, can be found (i.e. not null)
        assertNotNull(supplierManagement.findSupplier(new Product("phone", 5 ,10)));

        // Scenario #2 The item name is correctly set.
        assertTrue(supplierManagement.findSupplier(new Product("phone", 5 ,10))
            .getSupplierName().equals("DesignedAndMadeInChina"));

        // Scenario #3 The item phone is correctly set.
        assertTrue(supplierManagement.findSupplier(new Product("phone", 5 ,10))
                .getSupplierPhone().equals("+8613800138000"));

        // Scenario #4 The item email is correctly set.
        assertTrue(supplierManagement.findSupplier(new Product("phone", 5 ,10))
                .getSupplierEmail().equals("somewhereInChina@lol.cn"));
    }

    @Test
    public void removeSupplierFromList() throws Exception
    {
        supplierManagement.addSupplierToList(new Product("phone", 5 ,10),
                "DesignedAndMadeInChina", "somewhereInChina@lol.cn", "+8613800138000");

        supplierManagement.removeSupplierFromList(
                supplierManagement.findSupplier(new Product("phone", 5 ,10)));

        // Test if it correctly removed.
        assertNull(supplierManagement.findSupplier(new Product("phone", 5 ,10)));
    }

    @Test
    public void findSupplier() throws Exception
    {
        // Scenario #1: Search some non-existed items
        assertNull(supplierManagement.findSupplier(new Product("nothing", 5 ,10)));

        supplierManagement.addSupplierToList(new Product("phone", 5 ,10),
                "DesignedAndMadeInChina", "somewhereInChina@lol.cn", "+8613800138000");

        // Scenario #2: Search existing items
        assertNotNull(supplierManagement.findSupplier(new Product("phone", 5 ,10)));
    }

    @Test
    public void getSupplierTotalPrice() throws Exception
    {
        supplierManagement.addSupplierToList(new Product("phone", 5 ,10),
                "DesignedAndMadeInChina", "somewhereInChina@lol.cn", "+8613800138000");

        // Should be 50
        assertTrue(supplierManagement.getSupplierTotalPrice(
                supplierManagement.findSupplier(new Product("phone", 5 ,10))) == 50);
    }

    @Test
    public void saveSupplierList() throws Exception
    {
        // Load an existing file and save, then reload
        // This class has no load method because I've forgot and we have no time left lol!
        supplierManagement = new SupplierManagement("supplyList.json");

        supplierManagement.saveSupplierList("supplierManagementTest.json");

        supplierManagement = new SupplierManagement("supplierManagementTest.json");

        // Scenario #1: Internal list must not be null
        assertNotNull(supplierManagement.getRawSupplierList());

        // Scenario #2: Internal list must not be empty
        assertTrue(supplierManagement.getRawSupplierList().size() > 0);

    }

    @After
    public void afterTest()
    {
        // Remove temporary file
        File file = new File("supplierManagementTest.json");
        file.delete();
    }

}