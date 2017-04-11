package checkout;

import java.util.ArrayList;
import java.util.UUID;

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
    public int restock(){
        //get product from arrayList
        ArrayList<Product> productArrayList = Product.getProductList();
        for (int i=0; i<productArrayList.size(); i++){
            //if quantity is less than a certain amount
            //add new number of product
        }


        return 0;
    }
}
