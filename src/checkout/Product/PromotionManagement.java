package checkout.Product;

import java.lang.reflect.Type;
import java.util.*;
import java.io.*;

import checkout.SalesRecord.SalesRecordLine;
import checkout.util.*;
import com.google.gson.reflect.TypeToken;

public class PromotionManagement
{
    private ArrayList<Promotion> promotionList;
    
    public PromotionManagement()
    {
        this.promotionList = new ArrayList<>();
    }
    
    public PromotionManagement(String fileName)
    {
        // Init JSON database
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Load what we want to load...
        // The object type should be set to ArrayList<Promotion> in order to correctly parse from JSON file.
        this.promotionList = jsonDatabase.readObjectFromFile(fileName, new TypeToken<ArrayList<Promotion>>() {}.getType());
    }
    
    public ArrayList<Promotion> getPromotionList() { return this.promotionList; }
    
    public void setPromotionList(ArrayList<Promotion> promotionList) { this.promotionList = promotionList; }

    public void loadFromFile(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Load what we want to load...
        // The object type should be set to ArrayList<Promotion> in order to correctly parse from JSON file.
        this.promotionList = jsonDatabase.readObjectFromFile(fileName, new TypeToken<ArrayList<Promotion>>() {}.getType());
    }

    public void saveToFile(String fileName)
    {
        // Init JSON database object
        JsonDatabase jsonDatabase = new JsonDatabase();

        // Save what we want to save...
        jsonDatabase.saveObjectToJsonFile(this.promotionList, fileName);
    }

    public boolean addPromotion(Promotion promotion)
    {
        if(searchPromotion(promotion.getAppliedProduct()) == null || searchPromotion(promotion.getName()) == null)
        {
            promotionList.add(promotion);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean addPromotion(String promotionName, double promotionPrice, Product promotionProduct, int promotionCondition)
    {
        Promotion promotion = new Promotion(promotionName, promotionPrice, promotionProduct,promotionCondition);

        if(searchPromotion(promotionProduct) == null || searchPromotion(promotion.getName()) == null)
        {
            promotionList.add(promotion);
            return true;
        }
        else
        {
            return false;
        }

    }


    public boolean removePromotion(String promotionName)
    {
        Promotion promotion = searchPromotion(promotionName);

        if(promotion != null)
        {
            return promotionList.remove(promotion);
        }
        else
        {
            return false;
        }
    }

    public boolean removePromotion(Product product)
    {
        Promotion promotion = searchPromotion(product);

        if(promotion != null)
        {
            return promotionList.remove(promotion);
        }
        else
        {
            return false;
        }
    }

    public boolean modifyPromotion(Promotion promotion,
                                   String name, double discountAmount, Product appliedProduct, int condition)
    {
        if(promotion == null)
        {
            return false;
        }

        promotion.setName(name);
        promotion.setAppliedProduct(appliedProduct);
        promotion.setCondition(condition);
        promotion.setPrice(discountAmount);

        return true;
    }

    public Promotion searchPromotion(Product product)
    {
        for(Promotion promotion : this.promotionList)
        {
            // If match, return this Promotion object immediately and stop the procedure
            if(promotion.getAppliedProduct() == product)
            {
                return promotion;
            }
        }

        return null;
    }

    public Promotion searchPromotion(String promotionName)
    {
        for(Promotion promotion : this.promotionList)
        {
            // If match, return this Promotion object immediately and stop the procedure
            if(promotion.getName().equals(promotionName))
            {
                return promotion;
            }
        }

        return null;
    }
}
