package checkout;

import java.util.ArrayList;

public class ManagerView
{
    public static void main(String args[])
    {
        ArrayList<Product> productList = Product.getProductList();
    }

    public void placeOrder()
    {

        //After purchase made, check:
        //if (product level < some amount(what amount?))
        //{
        //    Send request to replenish stock level for product.
        //    ProductList.txt
        //    WarehouseStaff.restock();
        //}

    }

    public void generateSupplyReport()
    {

        //    Sort products from most revenue to least revenue.
        //
        //    ProductList.txt
        //    List products in that sorted order.
        //    Print productID, product name and revenue amount.
        //    Revenue amount = amount made from transactions.

    }
}