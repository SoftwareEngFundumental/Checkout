package checkout.Views;

import checkout.Product.*;
import checkout.Staff.*;
import checkout.util.DatePeriod;
import reportgen.SalesReportGenerator;
import reportgen.report2png.ReportPngGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


public class ManagerStaffView
{
    private ManagerStaff managerStaff = null;
    private StaffManagement staffManagement = null;
    private LoginToken loginToken = null;
    
    public ManagerStaffView(ManagerStaff staff, StaffManagement staffManagement, LoginToken loginToken)
    {
        this.managerStaff = staff;
        this.staffManagement = staffManagement;
        this.loginToken = loginToken;
    }
    
    public void managerMain()
    {
        System.out.println("\n[[ MANAGER - MAIN MENU ]]\n");
        System.out.println(
                "Pick a function (by entering index number): \n" +
                        "1. Get sales report\n" +
                        "2. Set discount/promo \n" +
                        "3. Set prices\n" +
                        "4. List product revenue\n" +
                        "5. Add Staff\n" +
                        "6. Remove Staff\n" +
                        "7. Change a staff's password\n" +
                        "8. Save staff details to disk\n" +
                        "9. Load staff details from disk\n" + 
                        "10. Log out and return staff login view\n"
        );


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
                getMonthReport();
                break;
            }
            case 2:
            {
                break;
            }
            case 3:
            {
                changePrice();
                break;
            }
            case 4:
            {
                break;
            }
            case 5:
            {
                addStaff();
                break;
            }
            case 6:
            {
                removeUser();
                break;
            }
            case 7:
            {
                changePassword();
                break;
            }
            case 8:
            {
                System.out.println("[INFO] Try saving cache to staff.json...");
                staffManagement.saveUsersToFile("staff.json");
                break;
            }
            case 9:
            {
                System.out.println("[INFO] Try to load staff.json...");
;               staffManagement.loadUsersFromFile("staff.json");
                break;
            }
            case 10:
            {
                managerLogOut();
            }
            default:
            {
                System.out.println("[ERROR] Wrong input, please try again...\n\n");
                managerMain();
            }
        }

    }

    public void getMonthReport()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[[ MANAGER - SALES REPORT GENERATOR ]]\n");
        System.out.println("Now please enter a period of time to generate the report.");
        System.out.print("Enter the start year (1900 to now): ");
        int startYear = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer
        System.out.print("\nEnter the start month (1 to 12): ");
        int startMonth = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer
        System.out.print("\nEnter the start date (1 to 31): ");
        int startDate = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer
        System.out.print("\nEnter the end year (1900 to now): ");
        int endYear = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer
        System.out.print("\nEnter the end month (1900 to now): ");
        int endMonth = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer
        System.out.print("\nEnter the end date (1900 to now): ");
        int endDate = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer
        System.out.print("\nEnter the path for report: ");
        String reportPath = scanner.nextLine();


        SalesReportGenerator salesReportGenerator = new SalesReportGenerator();
        ReportPngGenerator reportPngGenerator = new ReportPngGenerator();

        try
        {
            salesReportGenerator.generateReportString("Sales Record/", reportPath + ".txt",
                    new DatePeriod(startYear, startMonth, startDate, endYear, endMonth, endDate));
            reportPngGenerator.generatePicFromTextFile(reportPath + ".txt", reportPath + ".png");
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("[ERROR] Record file not found! Please try again!");
            getMonthReport();
        }
        catch (ParseException exception)
        {
            System.out.println("[ERROR] Record file corrupted!");
            getMonthReport();
        }
        catch (IOException exception)
        {
            System.out.println("[ERROR] Failed to read/write the files!");
            getMonthReport();
        }

        managerMain();
    }

    private void changePrice()
    {
        // Declare scanner & get ID
        Scanner scanner = new Scanner(System.in);
        System.out.println("[INFO] Enter your product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer

        // Declare ProductManagement and try find the product.
        ProductManagement productManagement = new ProductManagement("productList.json");
        Product selectedProduct = productManagement.searchProduct(productId);

        // If product not found, go over it again
        if(selectedProduct == null)
        {
            System.out.println("\n[ERROR] Product not found, please try again.\n");
            changePrice();
        }
        else
        {
            // Change the price
            System.out.println(String.format("[INFO] The original price is %f, Enter the new price: ", selectedProduct.getPrice()));
            selectedProduct.setPrice(scanner.nextInt());
            scanner.nextLine(); // Clear up the input buffer
        }

        productManagement.saveToFile("productList.json");
    }

    private void changePromo()
    {
        // Declare scanner & get ID
        Scanner scanner = new Scanner(System.in);
        System.out.println("[INFO] Enter your product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Clear up the input buffer

        // Declare ProductManagement and try find the product.
        ProductManagement productManagement = new ProductManagement("productList.json");
        Product selectedProduct = productManagement.searchProduct(productId);

        // If product not found, go over it again
        if(selectedProduct == null)
        {
            System.out.println("\n[ERROR] Product not found, please try again.\n");
            changePromo();
        }

        // Declare promotion management and get the scanner
        PromotionManagement promotionManagement = new PromotionManagement("promoList.json");
        System.out.println("\n[INFO] Enter promotion description: ");
        String promoInfo = scanner.nextLine();

        System.out.println("\n[INFO] Enter promotion price: ");
        double promoPrice = scanner.nextDouble();
        scanner.nextLine(); // Clean buffer

        System.out.println("\n[INFO] Enter promotion condition: ");
        int promoCondition = scanner.nextInt();
        scanner.nextLine(); // Clean buffer

        // Save the promo
        if(promotionManagement.addPromotion(promoInfo, promoPrice, selectedProduct, promoCondition))
        {
            System.out.println("\n[ERROR] Failed to add promotion, may be it has already recorded.\n");
            changePromo();
        }
        else
        {
            System.out.println("\n[INFO] Promo added, will be save to disk.\n");
            productManagement.saveToFile("promoList.json");
        }

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

    private void addStaff()
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
            managerMain();
        }
    }

    private void managerLogOut()
    {
        Scanner scanner = new Scanner(System.in);

        if(staffManagement.userLogout(loginToken.getStaff().getUserName()))
        {
            System.out.println("[INFO] Try saving cache to staff.json...");
            staffManagement.saveUsersToFile("staff.json");
            System.out.println("[INFO] Log out successful!!");
            StaffLoginView.main();
        }
        else
        {
            System.out.println("[INFO] Log out FAILED!");
            StaffLoginView.main();
        }
    }


    private void removeUser()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter username: ");
        String userName = scanner.nextLine();

        if(staffManagement.deleteUser(userName))
        {
            System.out.println("Remove successful!!");
            managerMain();
        }
        else
        {
            System.out.println("Remove FAILED!");
            managerMain();
        }
    }

    private void changePassword()
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
            managerMain();
        }
        else
        {
            System.out.println("Change FAILED!");
            managerMain();
        }
    }
}