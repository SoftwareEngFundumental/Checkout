package checkout.Views;

import checkout.Product.Product;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.valueOf;

public class WarehouseStaffView {

    private static void restock() {
        Scanner scanner = new Scanner(System.in);
        Product product = Product.scanProductID();
        int quantity = Product.askForQuantity();

        product.setQuantity(product.getQuantity() + quantity);
        product.saveProductInfoToList();
    }

    private static void setProductQuantity() {
        Scanner scanner = new Scanner(System.in);
        Product product = Product.scanProductID();
        int quantity = Product.askForQuantity();

        product.setQuantity(quantity);
        product.saveProductInfoToList();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Restock product\n" +
                "2. Set product quantity");
        System.out.print(">: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                restock();
                break;
            case "2":
                setProductQuantity();
                break;
            default:
                System.out.println("Invalid input. Please enter again.\n");
                main(args);
        }
    }
}
