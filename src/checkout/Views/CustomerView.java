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
                System.out.println("Cannot find product. Please scan again. \n");
                continue;
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

    private static int askForQuantity() {
        Scanner scanner = new Scanner(System.in);
        int quantity = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the quantity: ");
            String scanInput = scanner.nextLine();
            try {
                valueOf(scanInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter quantity again?\n");
                continue;
            }
            if (valueOf(scanInput) > 0) {
                quantity = valueOf(scanInput);
                validInput = true;
            }
        }
        return quantity;
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
            int quantity = askForQuantity();
            Product.deductQuantity(product, quantity);
            addProductToList(salesRecordArrayList,product,quantity);
        }
        return salesRecordArrayList;
    }



    public static void main(Customer customer)
    {
        ArrayList<SalesRecordLine> salesRecordArrayList = checkOut();

        SalesRecord salesRecord = new SalesRecord(salesRecordArrayList);
        salesRecord.applyLoyaltyPoint(customer);
        salesRecord.saveSaleRecord(customer);
        System.out.println("\n" + salesRecord);
    }
}
