package checkout.Customer;

import checkout.util.JsonDatabase;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class Customer
{
    private String ID;
    private String name;
    private CreditCard creditCard;
    private int point;

    public Customer(String name, CreditCard creditCard) {
        int newCustomerIDNumber = 0;
        boolean isAvailableForNewCustomer = false;
        do {
            newCustomerIDNumber++;
            if (getCustomerByID("c" + newCustomerIDNumber) == null) {
                isAvailableForNewCustomer = true;
            }
        }while (!isAvailableForNewCustomer);

        this.ID ="c"+newCustomerIDNumber;
        this.name = name;
        this.creditCard = creditCard;
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

    public void saveCustomerInfoToList() {
        ArrayList<Customer> customerList = getCustomerList();
        for (Customer customerInList:customerList) {
            if (customerInList.equals(this)) {
                int index = customerList.indexOf(customerInList);
                customerList.set(index,this);
            }
        }
        saveCustomerList(customerList);
    }

    public static Customer getCustomerByID(String ID) {
        ArrayList<Customer> customerArrayList = getCustomerList();
        for (int i = 0; i < customerArrayList.size(); i++) {
            Customer customer = customerArrayList.get(i);
            if (Objects.equals(customer.getID(), ID)) {
                return customer;
            }
        }
        return null;
    }

    public  static Customer scanCustomerID () {
        Scanner scanner = new Scanner(System.in);
        boolean hasValidInput = false;
        String scannedCustomerID;
        Customer customer = null;

        while (!hasValidInput) {
            System.out.print("Scan customer ID: ");
            scannedCustomerID = scanner.nextLine();

            customer = Customer.getCustomerByID(scannedCustomerID);

            if (customer == null) {
                System.out.println("Can't find customer. Please scan again: \n");
                continue;
            }

            hasValidInput = true;
        }
        return customer;
    }



    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
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
                ", credit card=" + creditCard +
                ", point=" + point +
                '}';
    }
}
