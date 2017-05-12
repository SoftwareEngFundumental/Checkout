package checkout.Views;

import checkout.Staff.LoginResult;
import checkout.Staff.StaffManagement;
import checkout.Staff.StaffType;
import checkout.Staff.WarehouseStaff;

import java.util.*;
import java.lang.*;

public class StaffView
{
    public static StaffManagement staffManagement = new StaffManagement();
    public static void main(String[] args)
    {
        loginMain();
    }

    private static void loginMain()
    {
        System.out.println("[INFO] Trying to load staff.json...");
        staffManagement.loadUsersFromFile("staff.json");

        System.out.println("\n[[\"Checkout\" Staff Console]] - [[LOGIN]]\n");

        LoginResult loginResult = doStaffLogin();

        if(loginResult.getLoginStatus())
        {
            System.out.println("\n[INFO] Login successful!\n");
            staffMain(loginResult);
        }
        else
        {
            System.out.println("\n[INFO] Login failed, please try again.");
            loginMain();
        }
    }
    
    private static void staffMain(LoginResult loginResult)
    {
        System.out.println(
                "Pick a function (by entering index number): \n" +
                        "1. Register a new staff\n" +
                        "2. Logout\n" +
                        "3. Remove a staff\n" +
                        "4. Change a staff's password\n" +
                        "5. Save staff details to JSON\n" +
                        "6. Load staff details from JSON\n" +
                        "7. Save status and quit"
        );

        switch(loginResult.getStaff().getUserType())
        {
            case SALES:
            {
                break;
            }
            case MANAGER:
            {
                System.out.println("8. Go to manager view");
                break;
            }
            case WAREHOUSE:
            {
                System.out.println("9. Go to warehouse staff view");
                break;
            }
        }

        // Create scanner
        Scanner scanner = new Scanner(System.in);

        // Record user choice
        int userChoice = scanner.nextInt();

        // Clean up next line, otherwise the new line character will still exist in the buffer
        // which will causes other issues later on.
        scanner.nextLine();

        switch(userChoice)
        {
            case 1:
            {
                addStaff(loginResult);
                break;
            }
            case 2:
            {
                doStaffLogout(loginResult);
                break;
            }
            case 3:
            {
                doRemoveUser(loginResult);
                break;
            }
            case 4:
            {
                doPasswordChange(loginResult);
                break;
            }
            case 5:
            {
                System.out.println("\n[DEBUG] Try saving to JSON...");
                staffManagement.saveUsersToFile("staff.json");
                staffMain(loginResult);
                break;
            }
            case 6:
            {
                System.out.println("\n[DEBUG] Try loading from JSON...");
                staffManagement.loadUsersFromFile("staff.json");
                staffMain(loginResult);
                break;
            }
            case 7:
            {
                Main.main(null);
                break;
            }
            case 8:
            {
                if(loginResult.getStaff().getUserType() == StaffType.MANAGER)
                {
                    ManagerStaffView.main(null);
                }
                else
                {
                    System.out.println("[ERROR] Permission denied.");
                }
                break;
            }
            case 9:
            {
                if(loginResult.getStaff().getUserType() == StaffType.WAREHOUSE)
                {
                    WarehouseStaffView.main(null);
                }
                else
                {
                    System.out.println("[ERROR] Permission denied.");
                }
            }
            default:
            {
                System.out.println("[ERROR] Wrong input, please try again...\n\n");
                main(null);
            }
        }
    }

    private static void addStaff(LoginResult loginResult)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter user name: ");
        String userName = scanner.nextLine();
        System.out.print("\nEnter password: ");
        String userPassword = scanner.nextLine();
        System.out.println("\nEnter user type: ");
        System.out.print("\n(Hint: it can be MANAGER, WAREHOUSE or SALES): ");
        String userTypeName = scanner.nextLine().toUpperCase();


        if(userName.length() < 1 && userPassword.length() < 1)
        {
            System.out.println("\n\n[Error] Invalid input for user name/password, please try again.");
            addStaff(loginResult);
        }


        StaffType staffType = null;

        try
        {
            staffType = StaffType.valueOf(userTypeName);
        }
        catch(IllegalArgumentException illegalInputException)
        {
            System.out.println("\n\n[Error] Invalid input for user type, please try again.");
            addStaff(loginResult);
        }


        staffManagement.createUser(staffType, userName, userPassword);

        System.out.println(String.format(
                "\n\n-------------------\n" +
                "New user details:\n" +
                        "Username: %s\n" +
                        "User password: %s\n" +
                        "User type %s\n" +
                        "-------------------\n", userName, userPassword, userTypeName));

        System.out.print("\nAdd more users?? (Y or N) ");
        String userAddMore = scanner.nextLine();

        if(userAddMore.toUpperCase().contains("Y"))
        {
            addStaff(loginResult);
        }
        else
        {
            staffMain(loginResult);
        }
    }

    private static LoginResult doStaffLogin()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();
        System.out.print("\nEnter password: ");
        String userPassword = scanner.nextLine();

        return staffManagement.userLogin(userName, userPassword);
    }

    private static void doStaffLogout(LoginResult loginResult)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();

        if(staffManagement.userLogout(userName))
        {
            System.out.println("Log out successful!!");
            loginMain();
        }
        else
        {
            System.out.println("Log out FAILED!");
            loginMain();
        }
    }


    private static void doRemoveUser(LoginResult loginResult)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();

        if(staffManagement.deleteUser(userName))
        {
            System.out.println("Remove successful!!");
            staffMain(loginResult);
        }
        else
        {
            System.out.println("Remove FAILED!");
            staffMain(loginResult);
        }
    }

    private static void doPasswordChange(LoginResult loginResult)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();
        System.out.print("\nEnter current password: ");
        String userOldPassword = scanner.nextLine();
        System.out.print("\nEnter new password: ");
        String userNewPassword = scanner.nextLine();

        if(staffManagement.changeUserPassword(userName, userOldPassword, userNewPassword))
        {
            System.out.println("Change successful!!");
            staffMain(loginResult);
        }
        else
        {
            System.out.println("Change FAILED!");
            staffMain(loginResult);
        }
    }

}
