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
//            Eliminate string
            try {
                valueOf(scannedProductID);
            }
            catch (NumberFormatException e) {
                System.out.println("Cannot find product. Please scan again. \n");
                continue;
            }
//            Ask for quantity
            if (Product.getProductByID(valueOf(scannedProductID)) != null) {
                System.out.print("Enter the quantity: ");
                String quantityInput = scanner.nextLine();
//                Eliminate String
                try {
                    valueOf(quantityInput);
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter quantity again?\n");
                    continue;
                }
//                Deduct the quantity from the inventory
                Product product = Product.getProductByID(valueOf(scannedProductID));
                Product.deductQuantity(product, valueOf(quantityInput));

//                Merge same item into same line
                boolean itemAlreadyInTheList = false;
                for (SaleRecordLine saleRecordLine :  salesRecordArrayList) {
//                    get ID for Item. switch class

                    if (saleRecordLine.getItem().getClass() == Product.class) {
                        Product scannedProduct = (Product)saleRecordLine.getItem();
                        if (scannedProduct.getID() == valueOf(scannedProductID)) {
                            itemAlreadyInTheList = true;
                            int index = salesRecordArrayList.indexOf(saleRecordLine);
                            saleRecordLine.setQuantity(saleRecordLine.getQuantity() + valueOf(quantityInput));
                            salesRecordArrayList.set(index,saleRecordLine);
                            System.out.println(saleRecordLine + "\n");
                        }
                    }
                }
//                Create new line if the product is not already scan
                if (!itemAlreadyInTheList) {
                    SaleRecordLine newSaleRecordLine = new SaleRecordLine(product, valueOf(quantityInput));
                    System.out.println(newSaleRecordLine + "\n");
                    salesRecordArrayList.add(newSaleRecordLine);
                }

                // TODO: 25/04/2017 Check Promo
                // TODO: 25/04/2017 customer loyalty point
            }
//            This is when the system cannot find the product from the database (the scanned ID is already an int)
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
