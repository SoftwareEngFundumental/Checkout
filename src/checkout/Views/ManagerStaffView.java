package checkout.Views;

import checkout.Product.*;
import checkout.Staff.*;
import checkout.Supply.SupplierManagement;
import checkout.util.DatePeriod;
import reportgen.SalesReportGenerator;
import reportgen.SupplyReportGenerator;
import reportgen.report2png.ReportPngGenerator;

import java.io.File;
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
                        "4. Add supplier\n" +
                        "5. Get supplier report\n" +
                        "6. Remove supplier\n" +
                        "7. Add Staff\n" +
                        "8. Remove Staff\n" +
                        "9. Change a staff's password\n" +
                        "10. Save staff details to disk\n" +
                        "11. Load staff details from disk\n" +
                        "12. Log out and return staff login view\n"
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
                changePromo();
                break;
            }
            case 3:
            {
                changePrice();
                break;
            }
            case 4:
            {
                setSupplier();
                break;
            }
            case 5:
            {
                getAllSupplyReport();
                break;
            }
            case 6:
            {
                removeSupplier();
                break;
            }
            case 7:
            {
                addStaff();
                break;
            }
            case 8:
            {
                removeUser();
                break;
            }
            case 9:
            {
                changePassword();
                break;
            }
            case 10:
            {
                System.out.println("[INFO] Try saving cache to staff.json...");
                staffManagement.saveUsersToFile("staff.json");
                break;
            }
            case 11:
            {
                System.out.println("[INFO] Try to load staff.json...");
;               staffManagement.loadUsersFromFile("staff.json");
                break;
            }
            case 12:
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

    private void getAllSupplyReport()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[[ MANAGER - SUPPLY REPORT GENERATOR ]]\n");
        System.out.println("Now please enter a period of time to generate the report.");
        System.out.print("Enter the path for report: ");
        String reportPath = scanner.nextLine();

        SupplyReportGenerator supplyReportGenerator = new SupplyReportGenerator("supplyList.json");
        ReportPngGenerator reportPngGenerator = new ReportPngGenerator();

        try
        {
            supplyReportGenerator.generateReportString(reportPath + ".txt");
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

        System.out.println("\n[INFO] Report generated.");
        managerMain();
    }

    private void setSupplier()
    {
        File file = new File("supplyList.json");
        System.out.println("\n[[ MANAGER - ADD SUPPLIER ]]\n");
        if(!file.exists())
        {
            try
            {
                if(file.createNewFile())
                {
                    System.out.println("[INFO] Supply list does not exist, so a new file has been created.");
                }
                else
                {
                    System.out.println("[ERROR] Supply list does not exist while the new file has failed to create.");
                    managerMain();
                }
            }
            catch(IOException ioException)
            {
                System.out.println("[ERROR] IO Exception occurs. Do you have write permission to your file system?");
                managerMain();
            }
        }

        Scanner scanner = new Scanner(System.in);

        SupplierManagement supplierManagement = new SupplierManagement("supplyList.json");
        ProductManagement productManagement = new ProductManagement("productList.json");

        System.out.print("\nEnter the product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        // The product may be null becuase the product ID may be invalid.
        Product product = productManagement.searchProduct(productID);

        if(product == null)
        {
            System.out.println("\n[ERROR] Product not found, please try again.");
            
            setSupplier();
        }

        System.out.print("\nEnter the supplier name: ");
        String supplierName = scanner.nextLine();

        System.out.print("\nEnter the supplier phone: ");
        String supplierPhone = scanner.nextLine();

        System.out.print("\nEnter the supplier email: ");
        String supplierEmail = scanner.nextLine();

        System.out.println("\n[INFO] Adding supplier information...");
        supplierManagement.addSupplierToList(product, supplierName, supplierEmail, supplierPhone);
        System.out.println("[INFO] Saving to file...");
        supplierManagement.saveSupplierList("supplyList.json");

        
        managerMain();
    }

    private void removeSupplier()
    {
        File file = new File("supplyList.json");

        System.out.println("\n[[ MANAGER - REMOVE SUPPLIER ]]\n");

        if(!file.exists())
        {
            System.out.println("[ERROR] Supply list does not exist!");
            managerMain();
        }

        Scanner scanner = new Scanner(System.in);
        SupplierManagement supplierManagement = new SupplierManagement("supplyList.json");

        System.out.print("Enter supplier ID: ");
        int supplierID = scanner.nextInt();
        scanner.nextLine();

        if(supplierManagement.removeSupplierFromList(supplierManagement.findSupplier(supplierID)))
        {
            System.out.println("[INFO] Supplier removed.");
        }
        else
        {
            System.out.println("[ERROR] Supplier not found.");
        }

        
        managerMain();

    }

    private void getMonthReport()
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
        System.out.println("\n[[ MANAGER - CHANGE PRICE ]]\n");
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

        System.out.println("[INFO] Record added, saving product to disk...");
        productManagement.saveToFile("productList.json");
        managerMain();
    }

    private void changePromo()
    {
        // Declare scanner & get ID
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[[ MANAGER - CHANGE PROMO ]]\n");
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
            System.out.println("\n[INFO] Promo added, will be save to disk.\n");
            promotionManagement.saveToFile("promoList.json");
            managerMain();
        }
        else
        {
            System.out.println("\n[ERROR] Failed to add promotion, may be it has already recorded.\n");
            changePromo();
        }

    }

    private void addStaff()
    {
        System.out.println("\n[[ MANAGER - ADD STAFF ]]\n");
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

        // Declare staffType, convert staff type string to StaffType enum
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


        // Create a user with specified user details
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
            Main.main(null);
        }
    }


    private void removeUser()
    {
        System.out.println("\n[[ MANAGER - REMOVE USER ]]\n");
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
        System.out.println("\n[[ MANAGER - CHANGE PASSWORD ]]\n");
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