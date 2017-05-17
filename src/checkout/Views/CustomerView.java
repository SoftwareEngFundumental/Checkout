package checkout.Views;

import checkout.Customer.Customer;
import checkout.Product.Product;
import checkout.SalesRecord.SalesRecordLine;
import checkout.SalesRecord.SalesRecord;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.valueOf;

public class CustomerView {

    private static Product askForProduct() {
        Scanner scanner = new Scanner(System.in);
        Product product = null;
        boolean validInput = false;

        while (!validInput){
            System.out.print("Scan your next item: ");
            String scanInput = scanner.nextLine();

            if (scanInput.isEmpty()) {
                return null;
            }

            try {
                valueOf(scanInput);
            } catch (NumberFormatException e) {
                if (scanInput.equals("staff")) {
                    StaffView.main(null);
                }
                else {
                    System.out.println("Cannot find product. Please scan again. \n");
                    continue;
                }
//            String
                // TODO: 07/05/2017 check for sales staff
            }

            validInput = true;
            product = Product.getProductByID(valueOf(scanInput));
            if (product == null) {
                System.out.println("Cannot find product. Please scan again.\n");
                validInput = false;
                continue;
            }
            return product;
        }
    return product;
    }

    private static void addProductToList(ArrayList<SalesRecordLine> salesRecordArrayList, Product product, int quantity) {
        boolean isAlreadyOnTheList = false;
        int indexOfSaleRecordLine = salesRecordArrayList.size();

        for (int index = 0; index < salesRecordArrayList.size(); index++) {
            SalesRecordLine salesRecordLine = salesRecordArrayList.get(index);
            if (salesRecordLine.hasProduct(product)) {
                isAlreadyOnTheList = true;
                indexOfSaleRecordLine = index;
                // TODO: 13/05/2017 dont break
                break;
            }
        }

//                Add to record line if the product existed
        if (isAlreadyOnTheList) {
            SalesRecordLine salesRecordLine = salesRecordArrayList.get(indexOfSaleRecordLine);
            salesRecordLine.setQuantity(salesRecordLine.getQuantity() + quantity);
            salesRecordArrayList.set(indexOfSaleRecordLine, salesRecordLine);
            System.out.println(salesRecordLine + "\n");
        }
        else {
//                Create new line if the product is not already scan
            SalesRecordLine newSalesRecordLine = new SalesRecordLine(product, quantity);
            System.out.println(newSalesRecordLine + "\n");
            salesRecordArrayList.add(newSalesRecordLine);
        }

    }

    private static ArrayList<SalesRecordLine> checkOut() {
        boolean doneEnteringItem = false;
        ArrayList<SalesRecordLine> salesRecordArrayList = new ArrayList<>();

        while (!doneEnteringItem) {
            Product product = askForProduct();
            if (product == null) {
                doneEnteringItem = true;
                continue;
            }
            int quantity = Product.askForQuantity();
            Product.deductQuantity(product, quantity);
            addProductToList(salesRecordArrayList,product,quantity);
        }
        return salesRecordArrayList;
    }



    public static void main() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("1. Check Out.\n" +
                "2. New customer? Join us.");
        System.out.print(">:");
        String input = scanner.nextLine();

        switch (input) {
            case "2":

                break;
            case "1":
                Customer customer = Customer.scanCustomerID();
                SalesRecord salesRecord = new SalesRecord(checkOut());

                salesRecord.applyLoyaltyPoint(customer);
                salesRecord.saveSaleRecord(customer);

                System.out.println("\n" + salesRecord);
                System.out.println("Thank you for shopping with us. Have a nice day.");
                break;
            default:
                System.out.println("invalid input. Please choose again.");
        }
        CustomerView.main();

        // TODO: 16/05/2017 create new Customer

    }
}
