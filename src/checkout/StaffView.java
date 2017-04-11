package checkout;

import java.util.*;
import java.io.*;
import java.lang.*;

public class StaffView
{
    public static StaffManagement staffManagement = new StaffManagement();
    public static void main(String[] args)
    {
        doDemo();
    }

    private static void doDemo()
    {
        System.out.println(
                "Milestone 1 Demo for Staff classes\n" +
                        "By \"Jackson\" Ming Hu (s3554025)\n"
        );
        System.out.println(
                "Pick a function: \n" +
                        "1. Register a new staff\n" +
                        "2. Login as a staff\n" +
                        "3. Logout\n" +
                        "4. Remove a staff\n" +
                        "5. Change a staff's password\n"
        );

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        scanner.nextLine();                         // Eat up the new line

        switch(userChoice)
        {
            case 1:
            {
                addStaff();
                break;
            }
            case 2:
            {
                doStaffLogin();
                break;
            }
            case 3:
            {
                doStaffLogout();
                break;
            }
            case 4:
            {
                doRemoveUser();
                break;
            }
            case 5:
            {
                doPasswordChange();
                break;
            }
        }
    }

    public static void addStaff()
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
            addStaff();
        }


        StaffType staffType = null;

        try
        {
            staffType = StaffType.valueOf(userTypeName);
        }
        catch(IllegalArgumentException illegalInputException)
        {
            System.out.println("\n\n[Error] Invalid input for user type, please try again.");
            addStaff();
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
            addStaff();
        }
        else
        {
            doDemo();
        }
    }

    private static void doStaffLogin()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();
        System.out.print("\nEnter password: ");
        String userPassword = scanner.nextLine();

        if(staffManagement.userLogin(userName, userPassword))
        {
            System.out.println("Login successful!!");
            doDemo();
        }
        else
        {
            System.out.println("Login FAILED!");
            doDemo();
        }
    }

    private static void doStaffLogout()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();

        if(staffManagement.userLogout(userName))
        {
            System.out.println("Log out successful!!");
            doDemo();
        }
        else
        {
            System.out.println("Log out FAILED!");
            doDemo();
        }
    }


    private static void doRemoveUser()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();

        if(staffManagement.deleteUser(userName))
        {
            System.out.println("Remove successful!!");
            doDemo();
        }
        else
        {
            System.out.println("Remove FAILED!");
            doDemo();
        }
    }

    private static void doPasswordChange()
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
            doDemo();
        }
        else
        {
            System.out.println("Change FAILED!");
            doDemo();
        }
    }

}
