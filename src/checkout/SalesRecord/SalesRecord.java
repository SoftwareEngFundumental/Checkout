package checkout.SalesRecord;

import checkout.Customer.Customer;
import checkout.JsonDatabase;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SalesRecord {

    private ArrayList<SaleRecordLine> recordLines;

    public SalesRecord(ArrayList<SaleRecordLine> recordLines) {
        this.recordLines = recordLines;
    }

    public void saveSaleRecord(Customer customer) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();

        String filepath = "Sales Record/" + dateFormat.format(date) + "-" + customer.getID() + ".json";
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(recordLines, filepath);
    }

    public void applyLoyaltyPoint(Customer customer) {
        // TODO: 04/05/2017 Loyalty Point
    }

    public static ArrayList<SaleRecordLine> getSaleRecord(String filename) {
        filename = "Sales Record/" + filename;
        Type productListType = new TypeToken<ArrayList<SaleRecordLine>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        return jsonDatabase.readObjectFromFile(filename, productListType);
    }

    @Override
    public String toString() {
        String output = "";
        for (SaleRecordLine recordLine : recordLines) {
            output += recordLine + "\n";
        }
        return output;
    }
}
