package test;

import checkout.Product.Product;
import checkout.Product.ProductManagement;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */
public class ProductManagementTest
{
    private ProductManagement productManagement;

    @Before
    public void setUp() throws Exception
    {
        productManagement = new ProductManagement("productManagementTest.json");
    }

    @Test
    public void loadFromFile() throws Exception
    {
        productManagement.loadFromFile("productList.json");

        // The existing file must be valid, so,
        // Scenario #1 The internal list is not null
        assertNotNull(productManagement.getRawProductList());

        // Scenario #2 The internal list has some items (i.e. not empty)
        assertTrue(productManagement.getRawProductList().size() > 0);
    }

    @Test
    public void saveToFile() throws Exception
    {
        // Load from another existing file and save, then load again and see if it correctly saved.
        productManagement.loadFromFile("productList.json");

        // Save it
        productManagement.saveToFile("productManagementTest.json");
        productManagement.loadFromFile("productManagementTest.json");

        // The saved file must be valid, so,
        // Scenario #1 The internal list is not null
        assertNotNull(productManagement.getRawProductList());

        // Scenario #2 The internal list has some items (i.e. not empty)
        assertTrue(productManagement.getRawProductList().size() > 0);

    }

    @Test
    public void addProduct() throws Exception
    {
        // Add a product first
        productManagement.addProduct(new Product("foobar", 5, 10));

        // Test if it correctly added
        assertNotNull(productManagement.searchProduct("foobar"));
    }

    @Test
    public void removeProduct() throws Exception
    {
        // Add a product first
        productManagement.addProduct(new Product("foobar", 5, 10));

        // Test if it correctly added
        assertNotNull(productManagement.searchProduct("foobar"));

        // Then test if it can be removed or not
        productManagement.removeProduct("foobar");
        assertNull(productManagement.searchProduct("foobar"));
    }

    @Test
    public void modifyProduct() throws Exception
    {
        // Add a product first
        productManagement.addProduct(new Product("foobar", 5, 10));

        // Test if it correctly added
        assertNotNull(productManagement.searchProduct("foobar"));

        // Test if it can be modified
        productManagement.modifyProduct(productManagement.searchProduct("foobar"),
                "f00bar", 10, 20, false);
    }

    @Test
    public void searchProduct() throws Exception
    {
        // Add a product first
        productManagement.addProduct(new Product("foobar", 5, 10));

        // Test if it correctly searched
        assertNotNull(productManagement.searchProduct("foobar"));

    }

    @After
    public void afterTest()
    {
        // Remove temporary file
        File file = new File("productManagementTest.json");
        file.delete();
    }

}