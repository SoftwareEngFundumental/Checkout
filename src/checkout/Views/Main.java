package checkout.Views;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Customer View\n" +
                            "2. Staff View");
        System.out.print(">: ");
        String input = scanner.nextLine();
        System.out.println();

        switch (input) {
            case "1":
            {
                CustomerView.main();
                break;
            }
            case "2":
            {
                StaffLoginView.main();
                break;
            }
            default:
            {
                System.out.println("Invalid input, try again.\n");
                Main.main(args);
                break;
            }
        }
    }
}
