package checkout;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import static java.lang.Integer.valueOf;

public class CustomerView {

    public static void scanItem() {
        boolean doneEnteringItem = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<SaleRecordLine> salesRecordArrayList = new ArrayList<SaleRecordLine>();


        String scannedProductID;
        while (!doneEnteringItem) {
            System.out.print("Scan your next item: ");
            scannedProductID = scanner.nextLine();

            if (scannedProductID.isEmpty()) {
                doneEnteringItem = true;
                continue;
            }
            try {
                valueOf(scannedProductID);
            }
            catch (NumberFormatException e) {
                System.out.println("Cannot find product. Please scan again. \n");
                continue;
            }
            if (Product.getProductByID(valueOf(scannedProductID)) != null) {
                System.out.print("Enter the quantity: ");
                String quantityInput = scanner.nextLine();
                try {
                    valueOf(quantityInput);
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter quantity again?\n");
                    continue;
                }
                SaleRecordLine saleRecordLine = new SaleRecordLine(valueOf(scannedProductID), valueOf(quantityInput));
                System.out.println(saleRecordLine + "\n");
                salesRecordArrayList.add(saleRecordLine);
            }
            else {
                System.out.println("Cannot find product. Please scan again.\n");
            }

        }
        SalesRecord salesRecord = new SalesRecord(salesRecordArrayList);
        salesRecord.saveSaleRecord();
        System.out.println("\n" + salesRecord);
    }

    public  static void scanCustomerID (TreeMap<Integer, Customer> customerTreeMap) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO: 11/04/2017 what if scan same item twice (can we merge?)
        scanItem();

        // TODO: 30/03/2017 scan customer ID (just like the products)
    }
}
