package checkout.Staff;

import checkout.Item.Product;

import java.util.ArrayList;

public class WarehouseStaff extends Staff
{
    public WarehouseStaff(String userName, String userPassword, int userId)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserId(userId);
    }

    /* method restock()
    * staff enters a number of product
    * staff gets id
    * add more inventory to product
    */

    public int restock()
    {
        //get product from arrayList
        ArrayList<Product> productArrayList = Product.getProductList();
        for (int i=0; i<productArrayList.size(); i++)
        {
            //if quantity is less than a certain amount
            if (productArrayList.get(i).getQuantity() < 500) {
                //update quantity of product
                productArrayList.get(i).setQuantity(500);
            }
        }

        return 0;
    }
}

