package checkout;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.valueOf;

public class CustomerView {

    public static ArrayList<SaleRecordLine> checkOut() {
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
                Product product = Product.getProductByID(valueOf(scannedProductID));
                Product.deductQuantity(product, valueOf(quantityInput));


                boolean itemAlreadyInTheList = false;
                for (SaleRecordLine saleRecordLine :  salesRecordArrayList) {
                    if (saleRecordLine.getProductID() == valueOf(scannedProductID)) {
                        itemAlreadyInTheList = true;
                        int index = salesRecordArrayList.indexOf(saleRecordLine);
                        saleRecordLine.setQuantity(saleRecordLine.getQuantity() + valueOf(quantityInput));
                        salesRecordArrayList.set(index,saleRecordLine);
                        System.out.println(saleRecordLine + "\n");
                    }
                }
                if (!itemAlreadyInTheList) {
                    SaleRecordLine newSaleRecordLine = new SaleRecordLine(product.getID(), valueOf(quantityInput));
                    System.out.println(newSaleRecordLine + "\n");
                    salesRecordArrayList.add(newSaleRecordLine);
                }
            }
            else {
                System.out.println("Cannot find product. Please scan again.\n");
            }
        }
        return salesRecordArrayList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO: 11/04/2017 what if scan same item twice (can we merge?)

        ArrayList<SaleRecordLine> salesRecordArrayList = checkOut();
        Customer customer = Customer.scanCustomerID();

        SalesRecord salesRecord = new SalesRecord(salesRecordArrayList);
        salesRecord.saveSaleRecord(customer.getID());
        System.out.println("\n" + salesRecord);
    }
}
