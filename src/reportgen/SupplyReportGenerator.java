package reportgen;


import checkout.Staff.StaffManagement;
import checkout.Supply.Supplier;
import checkout.Supply.SupplierManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */

public class SupplyReportGenerator
{
    private SupplierManagement supplierManagement = null;

    public SupplyReportGenerator(String recordPath)
    {
        this.supplierManagement = new SupplierManagement(recordPath);
    }

    public void generateReportString(String reportPath) throws ParseException, IOException
    {
        // Declare FileWriter and BufferedWriter
        FileWriter fileWriter = new FileWriter(reportPath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Date currentDate = new Date();

        // Write title
        bufferedWriter.write("-----------------------------------\n");

        bufferedWriter.write(String.format("Report created @ %s...\n",
                new SimpleDateFormat("HH:mm:ss - dd MMM, yyyy").format(currentDate)));

        bufferedWriter.write("-----------------------------------\n");

        // Get supplier list
        ArrayList<Supplier> supplierList = supplierManagement.getRawSupplierList();

        // Iterate the list
        for(Supplier supplier : supplierList)
        {
            bufferedWriter.write("-----------------------------------\n");

            // Print supplier information
            bufferedWriter.write(String.format(
                    "Supplier ID: %d\n" +
                            "Supplier Name: %s\n" +
                            "Supplier Phone: %s\n" +
                            "Supplier Email: %s\n" +
                            "Transaction Date: %s\n",

                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getSupplierPhone(),
                    supplier.getSupplierEmail(),
                    new SimpleDateFormat("HH:mm:ss - dd MMM, yyyy").format(supplier.getDate())
            ));

            bufferedWriter.write(String.format(
                    "Product ID: %d\n" +
                            "Product Name: %s\n" +
                            "Product total price: %f\n",

                    supplier.getProduct().getID(),
                    supplier.getProduct().getName(),
                    supplierManagement.getSupplierTotalPrice(supplier)));

            bufferedWriter.write("-----------------------------------\n");

            bufferedWriter.flush();
        }

        // Finally, flush and close
        bufferedWriter.flush();
        bufferedWriter.close();

    }
}
