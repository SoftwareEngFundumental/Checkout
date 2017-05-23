package reportgen;

import java.text.*;
import java.util.*;
import java.io.*;
import checkout.SalesRecord.*;
import checkout.Product.*;
import checkout.util.DatePeriod;

public class SalesReportGenerator
{
    public void generateReportString(String pathName, String reportPath, DatePeriod datePeriod) throws ParseException, IOException
    {
        // Put all files' name in the directory into the name list, then parse it later.
        ArrayList<String> fileNames = new ArrayList<>();

        // Get all the JSON file names in the sales records path
        File fileOfPath = new File(pathName);
        for(File fileInPath : fileOfPath.listFiles())
        {
            fileNames.add(fileInPath.getName());
        }

        // Time to generate the report!
        FileWriter fileWriter = new FileWriter(reportPath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("-----------------------------------\n");
        Date currentDate = new Date();

        bufferedWriter.write(String.format("Report created @ %s...\n",
                new SimpleDateFormat("HH:mm:ss - dd MMM, yyyy").format(currentDate)));

        double totalSalesIncome = 0;

        // Go through the JSON file in the sales record path one by one
        for(String fileName : fileNames)
        {
            // Detect if this JSON file is in the date period or not...
            if(DatePeriod.isDateInDatePeriod(getTimeFromFilename(fileName), datePeriod))
            {
                // If it is in the date period, then load it anyway!
                ArrayList<SalesRecordLine> salesRecordLines = SalesRecord.getSaleRecord(fileName);
                bufferedWriter.write(getSingleSalesReportString(salesRecordLines, getTimeFromFilename(fileName)));

                // Calculate the total income
                for(SalesRecordLine salesRecordLine : salesRecordLines)
                {
                    totalSalesIncome += (salesRecordLine.getQuantity() * salesRecordLine.getProduct().getPrice());
                }

                bufferedWriter.flush();
            }
            else
            {
                // If it's not, skip it...
                System.out.println(
                        String.format("[WARN] Skipping report file %s which is not in specified time span.", fileName));
            }
        }

        bufferedWriter.write(String.format("\n\nTotal income: $%.2f\n\n", totalSalesIncome));
        bufferedWriter.write("-----------------------------------\n");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private String getSingleSalesReportString(ArrayList<SalesRecordLine> salesRecordLines, Date recordDate) throws IOException
    {

        StringBuilder recordStr = new StringBuilder("-----------------------------------\n");

        /*
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
            Product product = salesRecordLine.getProduct();
            recordStr.append(String.format("%d\t%s\t%.2f\t%d\n",
                    index, product.getName(), product.getPrice(), salesRecordLine.getQuantity()));

            // Count the index and total price
            index++;
            totalPrice += product.getPrice() * salesRecordLine.getQuantity();
        }

        recordStr.append(String.format("\n\nTotal price $%.2f, item amount %d\n\n", totalPrice, index));
        recordStr.append("-----------------------------------\n");

        return recordStr.toString();
    }


    /**
     * File name is the full sales record file name, example: "20170509-135952-c1.json"
     *
     * In this project, since sales record related classes didn't implemented any time information APIs inside
     * themselves, instead, they've just put the time details in the file name. As a result, I have to "manually"
     * detect the files and convert it back to native Java Date.
     * */
    private Date getTimeFromFilename(String fileName) throws ParseException
    {
        // Convert original file name into a date in string (i.e. it just remove the ".json" filename extension)
        String dateStr = fileName.split("-")[0] + "-"+ fileName.split("-")[1];

        // Parse the string into Date and return.
        return new SimpleDateFormat("yyyyMMdd-HHmmss").parse(dateStr);
    }
}
