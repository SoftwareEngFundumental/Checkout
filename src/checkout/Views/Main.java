package checkout.Views;
import checkout.Customer.Customer;
import checkout.Views.CustomerView;
import checkout.Views.ManagerStaffView;
import checkout.Views.WarehouseStaffView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Customer View\n" +
                            "2. Manager View\n" +
                            "3. Warehouse Staff View\n");
        System.out.print(">: ");
        String input = scanner.nextLine();


        switch (input) {
            case "1":
                Customer customer = Customer.scanCustomerID();
                CustomerView.main(customer);
                break;
            case "2":
                ManagerStaffView.main(args);
                break;
            case "3":
                WarehouseStaffView.main(args);
                break;
            default:
        }
    }
}
