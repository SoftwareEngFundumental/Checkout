package checkout.Views;

import checkout.Product.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class ManagerStaffView
{
    public static void main(String args[]) {
        ArrayList<Product> productList = Product.getProductList();





    }

    public void getMonthReport() {
        File folder = new File("Sales Record/");
        File[] listOfFiles = folder.listFiles();

        for (File file: listOfFiles) {
            System.out.println(file.toString());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Month (1-12): ");
        String input = scanner.nextLine();

        try {
            valueOf(input);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please scan again. \n");
//            String
        }
    }

    public void placeOrder() {

        //After purchase made, check:
        //if (product level < some amount(what amount?))
        //{
        //    Send request to replenish stock level for product.
        //    ProductList.txt
        //    WarehouseStaff.restock();
        //}

    }

    public void generateSupplyReport() {

        //    Sort products from most revenue to least revenue.
        //
        //    ProductList.txt
        //    List products in that sorted order.
        //    Print productID, product name and revenue amount.
        //    Revenue amount = amount made from transactions.

    }
}