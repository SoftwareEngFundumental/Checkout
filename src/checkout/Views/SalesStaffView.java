package checkout.Views;

import checkout.SalesRecord.SalesRecordLine;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class SalesStaffView {

    private static ArrayList<SalesRecordLine> removeItem(ArrayList<SalesRecordLine> salesRecordArrayList, int index) {
        if (index >= salesRecordArrayList.size()) {
            return salesRecordArrayList;
        }
        else {
            salesRecordArrayList.remove(index);
            return salesRecordArrayList;
        }
    }

    private static ArrayList<SalesRecordLine> emptyCart(ArrayList<SalesRecordLine> salesRecordArrayList) {
        salesRecordArrayList.clear();
        return salesRecordArrayList;
    }

    public static ArrayList<SalesRecordLine> main(ArrayList<SalesRecordLine> salesRecordArrayList) {
        for (int i = 0; i < salesRecordArrayList.size(); i++) {
            int lineNum = i+1;
            System.out.println(lineNum + "// \t" + salesRecordArrayList.get(i));
        }


        System.out.println("-------------------------------------------------------\n" +
                "1. Remove item\n" +
                "2. Empty cart");
        Scanner scanner = new Scanner(System.in);
        System.out.print(">: ");
        String input = scanner.nextLine();

        switch (input){
            case "1":
                System.out.print("Which item do you want to remove? ");
                String index = scanner.nextLine();
                System.out.println();
                try{
                    salesRecordArrayList = removeItem(salesRecordArrayList, valueOf(index)-1);
                    salesRecordArrayList = main(salesRecordArrayList);
                }catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter again.\n");
                    salesRecordArrayList = main(salesRecordArrayList);
                }
                break;

            case "2":
                salesRecordArrayList = emptyCart(salesRecordArrayList);
                break;

            case "":
                break;

            default:
                System.out.println("Invalid input. Please enter again.\n");
                salesRecordArrayList = main(salesRecordArrayList);
        }
        return salesRecordArrayList;
    }
}
