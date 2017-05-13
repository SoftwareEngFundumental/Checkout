package checkout.Views;

import checkout.Product.Product;
import checkout.Staff.ManagerStaff;
import checkout.util.DatePeriod;
import reportgen.SalesReportGenerator;
import reportgen.report2png.ReportPngGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class ManagerStaffView
{
    public static void main(String args[])
    {
        System.out.println("\n[[ MANAGER - MAIN MENU ]]\n");
        System.out.println(
                "Pick a function (by entering index number): \n" +
                        "1. Get sales report\n" +
                        "2. Set discount/promo \n" +
                        "3. Set prices\n" +
                        "4. List product revenue\n" +
                        "5. Return to main staff menu\n"
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
                break;
            }
            case 4:
            {
                break;
            }
            case 5:
            {
                break;
            }
            default:
            {
                System.out.println("[ERROR] Wrong input, please try again...\n\n");
                main(null);
            }
        }

    }

    public static void getMonthReport()
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

        ManagerStaffView.main(null);
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