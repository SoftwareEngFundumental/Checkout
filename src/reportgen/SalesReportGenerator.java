package reportgen;

import java.text.*;
import java.time.LocalDateTime;
import java.util.*;
import java.io.*;
import checkout.SalesRecord.*;
import checkout.Item.*;

/**
 * Created by hu on 10/5/17.
 */
public class SalesReportGenerator
{

    public String generateReportString(String pathName) throws ParseException
    {
        // Put all files' name in the directory into the name list, then parse it later.
        ArrayList<String> fileNames = new ArrayList<>();

        File fileOfPath = new File(pathName);

        for(File fileInPath : fileOfPath.listFiles())
        {
            fileNames.add(fileInPath.getName());
        }

        // Time to generate the report!
        StringBuilder recordStr = new StringBuilder("-----------------------------------\n");

        recordStr.append(String.format("Report created @ %s...\n",
                new SimpleDateFormat("HH:mm:ss - dd MMM, yyyy").format(LocalDateTime.now())));

        double totalSalesIncome = 0;

        for(String fileName : fileNames)
        {
            ArrayList<SalesRecordLine> salesRecordLines = SalesRecord.getSaleRecord(fileName);
            recordStr.append(generateSingleSalesReportString(salesRecordLines, getTimeFromFilename(fileName)));

            for(SalesRecordLine salesRecordLine : salesRecordLines)
            {
                totalSalesIncome += (salesRecordLine.getQuantity() * salesRecordLine.getItem().getPrice());
            }
        }

        recordStr.append(String.format("\n\nTotal income: $%f\n\n", totalSalesIncome));

        return recordStr.toString();
    }

    protected String generateSingleSalesReportString(ArrayList<SalesRecordLine> salesRecordLines, Date recordDate)
    {

        StringBuilder recordStr = new StringBuilder("-----------------------------------\n");

        /**
         * Get the time string only, in the format of "yyyyMMdd-HHmmss".
         * For the detailed date format, please refer to:
         *      https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
         * */
        recordStr.append(String.format("Date @ %s, got %d records.\n\n",
                new SimpleDateFormat("HH:mm:ss - dd MMM, yyyy").format(recordDate),
                salesRecordLines.size()));

        // Print the header
        int index = 1;
        double totalPrice = 0;
        recordStr.append("ID\tName\tPrice\tAmount\n");

        // Get each sales record
        for(SalesRecordLine salesRecordLine : salesRecordLines)
        {
            Item item = salesRecordLine.getItem();
            recordStr.append(String.format("%d\t%s\t%f\t%d\n",
                    index, item.getName(), item.getPrice(), salesRecordLine.getQuantity()));

            // Count the index and total price
            index++;
            totalPrice += item.getPrice() * salesRecordLine.getQuantity();
        }

        recordStr.append(String.format("\n\nTotal price $ %f, item amount %d\n\n", totalPrice, index));
        recordStr.append("-----------------------------------\n");

        return recordStr.toString();
    }


    /** File name is the full sales record file name, example: "20170509-135952-c1.json" */
    private Date getTimeFromFilename(String fileName) throws ParseException
    {
        String dateStr = fileName.split("-")[0] + "-"+ fileName.split("-")[1];
        return new SimpleDateFormat("yyyyMMdd-HHmmss").parse(dateStr);
    }
}
