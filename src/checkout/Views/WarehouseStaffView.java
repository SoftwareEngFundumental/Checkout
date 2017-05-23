package checkout.Views;

import checkout.Product.Product;
import checkout.Staff.LoginToken;
import checkout.Staff.ManagerStaff;
import checkout.Staff.StaffManagement;
import checkout.Staff.WarehouseStaff;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class WarehouseStaffView
{
    private WarehouseStaff warehouseStaff = null;
    private StaffManagement staffManagement = null;
    private LoginToken loginToken = null;

    public WarehouseStaffView(WarehouseStaff staff, StaffManagement staffManagement, LoginToken loginToken)
    {
        this.warehouseStaff = staff;
        this.staffManagement = staffManagement;
        this.loginToken = loginToken;
    }

    private void restock()
    {
        Scanner scanner = new Scanner(System.in);
        Product product = Product.scanProductID();
        int quantity = Product.askForQuantity();

        product.setQuantity(product.getQuantity() + quantity);
        product.saveProductInfoToList();
    }

    private void setProductQuantity()
    {
        Scanner scanner = new Scanner(System.in);
        Product product = Product.scanProductID();
        int quantity = Product.askForQuantity();

        product.setQuantity(quantity);
        product.saveProductInfoToList();
    }

    public void warehouseMain()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Restock product\n" +
                "2. Set product quantity");
        System.out.print(">: ");
        String input = scanner.nextLine();

        switch (input)
        {
            case "1":
            {
                restock();
                break;
            }
            case "2":
            {
                setProductQuantity();
                break;
            }
            default:
            {
                System.out.println("Invalid input. Please try again.\n");
                warehouseMain();
                break;
            }
        }
    }
}
