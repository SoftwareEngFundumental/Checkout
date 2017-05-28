package test;

import checkout.Product.Product;
import checkout.Product.Promotion;
import checkout.Product.PromotionManagement;
import org.junit.*;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */

public class PromotionManagementTest
{
    private PromotionManagement promotionManagement;

    @Before
    public void setUp() throws Exception
    {
        promotionManagement = new PromotionManagement("promotionManagementTest.json");
    }

    @Test
    public void loadFromFile() throws Exception
    {
        // Load the existing list and see if it can be loaded
        promotionManagement.loadFromFile("promoList.json");

        // Scenario #1 Internal list is not null
        assertNotNull(promotionManagement.getPromotionList());

        // Scenario #2 Internal list is not empty
        assertTrue(promotionManagement.getPromotionList().size() > 0);
    }

    @Test
    public void saveToFile() throws Exception
    {
        // Load the existing list, save to somewhere else and reload
        promotionManagement.loadFromFile("promoList.json");

        // Scenario #1 Internal list can be saved
        promotionManagement.saveToFile("promotionManagementTest.json");
        promotionManagement.loadFromFile("promotionManagementTest.json");
        assertNotNull(promotionManagement.getPromotionList());
        assertTrue(promotionManagement.getPromotionList().size() > 0);
    }

    @Test
    public void addPromotion() throws Exception
    {
        promotionManagement.addPromotion("Test promo", -1.5,
                new Product("foobar", 10, 100), 50);

        // Scenario #1 Find promo and it is not null
        Promotion promotion = promotionManagement.searchPromotion("Test promo");
        assertNotNull(promotion);

        // Scenario #2 Promo status is correctly set, so that it can be applied
        assertTrue(promotion.getAppliedProduct().getName().equals("foobar"));
        assertTrue(promotion.getCondition() == 50);
    }

    @Test
    public void removePromotion() throws Exception
    {
        promotionManagement.addPromotion("Test promo", -1.5,
                new Product("foobar", 10, 100), 50);

        promotionManagement.removePromotion("Test promo");

        // Test if it is removed, i.e. return null.
        assertNull(promotionManagement.searchPromotion("Test promo"));
    }

    @Test
    public void modifyPromotion() throws Exception
    {
        promotionManagement.addPromotion("Test promo", -1.5,
                new Product("foobar", 10, 100), 50);

        promotionManagement.modifyPromotion(promotionManagement.searchPromotion("Test promo"),
                "Test pr0m0", -15,
                promotionManagement.searchPromotion("Test promo").getAppliedProduct(), 30);

        // Scenario #1 test if the promo name changed
        assertNull(promotionManagement.searchPromotion("Test promo"));

        // Scenario #2 test if the promo condition is set
        assertTrue(promotionManagement.searchPromotion("Test pr0m0").getCondition() == 30);

        // Scenario #3 test if the discount amount is set
        assertTrue(promotionManagement.searchPromotion("Test pr0m0").getPrice() == -15);
    }

    @Test
    public void searchPromotion() throws Exception
    {
        promotionManagement.addPromotion("Test promo", -1.5,
                new Product("foobar", 10, 100), 50);

        // Scenario #1 search some non-existed promotions
        assertNull(promotionManagement.searchPromotion("nothing"));

        // Scenario #2 search some non-existed promotions
        assertNotNull(promotionManagement.searchPromotion("Test promo"));
    }

    @After
    public void afterTest()
    {
        // Remove temporary file
        File file = new File("promotionManagementTest.json");
        file.delete();
    }

}