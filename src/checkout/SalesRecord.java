package checkout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class SalesRecord {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private Date date;
    private TreeMap<Product, Integer> receipt;
    private Customer customer;

    public SalesRecord() {
        date = new Date();
        receipt = new TreeMap<Product, Integer>();
    }
}
