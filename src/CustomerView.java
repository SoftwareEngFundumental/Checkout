import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import static java.lang.Integer.valueOf;

public class CustomerView {

    public static void scanItem(TreeMap<Integer, Product> productTreeMap) {
        boolean doneEnteringItem = false;
        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Cannot find product. Please scan again.\n");
                continue;
            }

            if (!productTreeMap.containsKey(valueOf(scannedProductID))) {
                System.out.println("Cannot find product. Please scan again.\n");
                continue;
            }
            System.out.println(productTreeMap.get(valueOf(scannedProductID)) + "\n");
        }
    }

    public  static void scanCustomerID (TreeMap<Integer, Customer> customerTreeMap) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        scanItem(Product.getProductList());
        System.out.println();
        // TODO: 30/03/2017 ask for quantity (or just leave it and scan multiple times)
        // TODO: 30/03/2017 save to receipt


        System.out.print("Enter your customer ID: ");
        // TODO: 30/03/2017 scan customer ID (just like the products)
    }
}
