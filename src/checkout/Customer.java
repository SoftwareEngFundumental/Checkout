package checkout;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Customer
{
    private int ID;
    private String name;
    private int cardNumber;
    private int point;

    public Customer(String name, int cardNumber) {
        this.ID = getCustomerList().size() + 1;
        this.name = name;
        this.cardNumber = cardNumber;
        this.point = 0;
    }



    public static ArrayList<Customer> getCustomerList() {
        Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        return jsonDatabase.readObjectFromFile("customerList.json", customerListType);
    }

    public static void saveCustomerList(ArrayList<Customer> customerArrayList) {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(customerArrayList, "customerList.json");
    }

    public static Customer getCustomerByID(int ID) {
        ArrayList<Customer> customerArrayList = getCustomerList();
        Customer customer = null;
        for (int i = 0; i < customerArrayList.size(); i++) {
            Customer temp = customerArrayList.get(i);
            if (temp.getID() == ID) {
                customer = temp;
            }
        }
        return customer;
    }

    public  static Customer scanCustomerID () {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        String scannedCustomerID;
        Customer customer = null;

        while (!validInput) {
            System.out.print("Scan customer ID: ");
            scannedCustomerID = scanner.nextLine();

            try {
                valueOf(scannedCustomerID);
            } catch (NumberFormatException e) {
                System.out.println("Can't find customer. Please scan again: \n");
                continue;
            }

            customer = Customer.getCustomerByID(valueOf(scannedCustomerID));

            if (customer == null) {
                System.out.println("Can't find customer. Please scan again: \n");
                continue;
            }

            validInput = true;
        }
        return customer;
    }



    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        // TODO: 11/04/2017 To String
        return "Customer{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", cardNumber=" + cardNumber +
                ", point=" + point +
                '}';
    }
}
