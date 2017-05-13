package checkout.Views;
import checkout.Customer.Customer;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Customer View\n" +
                            "2. Staff View\n");
        System.out.print(">: ");
        String input = scanner.nextLine();


        switch (input) {
            case "1":
                Customer customer = Customer.scanCustomerID();
                CustomerView.main(customer);
                break;
            case "2":
                StaffView.main(args);
                break;
            default:
        }
    }
}
