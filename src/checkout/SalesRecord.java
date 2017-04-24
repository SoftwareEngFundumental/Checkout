package checkout;

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

    public void saveSaleRecord(int customerID) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        String filepath = "Sales Record/" + dateFormat.format(date) + "-" + customerID + ".json";
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(recordLines, filepath);
    }

    public static ArrayList<SaleRecordLine> getSaleRecord(String filename) {
        filename = "Sales Record/" + filename;
        Type productListType = new TypeToken<ArrayList<SaleRecordLine>>() {}.getType();
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<SaleRecordLine> saleRecordLineArrayList = jsonDatabase.readObjectFromFile(filename, productListType);
        return saleRecordLineArrayList;
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
