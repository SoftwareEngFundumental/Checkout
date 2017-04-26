package checkout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.Integer.valueOf;
import java.util.*;
import java.lang.System;


public class Promo
{




/*

    ATTRIBUTES:

- promoAmount: double
- item: TreeMap<Product, promoDiscount>


    FUNCTIONS:

- addPromoCondition(): double
- applyPromoDiscount(): double

*/

    private double promoCondition;
    private double promoDiscount;
    private Boolean userLoginStatus;

    public Boolean getUserLoginStatus() { return userLoginStatus; }
    public void addPromoCondition(Staff staff, Product product) {

    /* need to be logged in as manager
     - check boolean 'userLoginStatus = T'
     - check boolean 'userType = Manager'*/



        String userName = staff.getUserName();

        /*private double promoCondition =*/

        System.out.println("please enter product quantity to qualify for discount");
        Scanner A = new Scanner(System.in);
        double promoConditionVal= A.nextDouble();


        System.out.println("please enter discount amount eg. 10, 20, 50 etc.");
        Scanner B = new Scanner(System.in);
        double promoAmount = B.nextDouble();


        promoDiscount = promoAmount * 0.01;



    }



    public void applyPromoDiscount(Product product) {

    /*need to import product name, ID and price
     - must be able to see what discount amount product has
     - must automatically apply discount when condition met*/

        System.out.println("your discount amount is" + promoDiscount * 100 + "% off");

        double discountedPrice = product.getPrice() * promoDiscount;

        System.out.println("new price is $" + discountedPrice);


        this.promoDiscount = promoDiscount;
    }



    /*
    public static TreeMap<Integer, product> getProductList(){
        TreeMap<Integer, Product> productTreMap = new TreeMap<Integer, Product>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader("productList.json"));
            String line = br.readLine();
            while (line!=null) {
                String[] parts = line.split("-");
                Integer id = valueOf(parts[0]);
                String name = parts[1];
                Double price = Double.parseDouble(parts[2]);
                productTreMap.put(id, new Product(name, price));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productTreMap;

    }
    */
}
