package checkout.Views;

import checkout.Staff.LoginToken;
import checkout.Staff.ManagerStaff;
import checkout.Staff.StaffManagement;
import checkout.Staff.WarehouseStaff;

import java.util.*;
import java.lang.*;

public class StaffLoginView
{
    public static StaffManagement staffManagement = new StaffManagement();
    public static void main()
    {
        loginMain();
    }

    private static void loginMain()
    {
        // Load the staff related JSON files into staffManagement
        System.out.println("[INFO] Trying to load staff.json...");
        staffManagement.loadUsersFromFile("staff.json");

        System.out.println("\n[[\"Checkout\" Staff Console]] - [[LOGIN]]");

        // Jump to login method and get the login token
        LoginToken loginToken = doStaffLogin();

        if(loginToken.getLoginStatus())
        {
            System.out.println("\n[INFO] Login successful!\n");

            switch(loginToken.getStaff().getUserType())
            {
                case WAREHOUSE:
                {
                    WarehouseStaffView warehouseStaffView = new WarehouseStaffView(
                            (WarehouseStaff)loginToken.getStaff(), staffManagement, loginToken);
                    warehouseStaffView.warehouseMain();
                    break;
                }
                case MANAGER:
                {
                    ManagerStaffView managerStaffView = new ManagerStaffView(
                            (ManagerStaff)loginToken.getStaff(),
                            staffManagement,
                            loginToken);

                    managerStaffView.managerMain();
                    break;
                }
                case SALES:
                {
                    break;
                }
            }
        }
        else
        {
            System.out.println("\n[INFO] Login failed, please try again.");
            loginMain();
        }
    }

    private static LoginToken doStaffLogin()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("\nEnter password: ");
        String userPassword = scanner.nextLine();

        return staffManagement.userLogin(userName, userPassword);
    }

}
