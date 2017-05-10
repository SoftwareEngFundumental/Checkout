package checkout.Views;

import java.util.Scanner;

public class WarehouseStaffView {

    private void restock() {
        Scanner scanner = new Scanner(System.in);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Restock product\n" +
                "2. Set product quantity");
        System.out.print(">: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                break;
            case "2":
                break;
            default:
        }

    }
}
