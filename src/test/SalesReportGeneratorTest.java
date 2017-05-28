package test;

import checkout.util.DatePeriod;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;
import checkout.SalesRecord.*;
import reportgen.report2png.*;
import reportgen.*;

import java.io.File;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */

public class SalesReportGeneratorTest
{
    private SalesReportGenerator salesReportGenerator;
    private ReportPngGenerator reportPngGenerator;

    @Before
    public void setUp() throws Exception
    {
        salesReportGenerator = new SalesReportGenerator();
        reportPngGenerator = new ReportPngGenerator();
    }

    @AfterClass
    public static void tearDown() throws Exception
    {

    }

    @Test
    public void generateReportString() throws Exception
    {
        // Generate string save, see if the file exists later on.
        // Since this cannot be parsed again, a human is needed in order to check if the file is correctly generated.
        salesReportGenerator.generateReportString("Sales Record/", "report.txt",
                new DatePeriod(2017, 5,1,2017,6,1));
        File file = new File("report.txt");
        assertTrue(file.exists());
    }

    @Test
    public void generateReportPic() throws Exception
    {
        salesReportGenerator.generateReportString("Sales Record/", "report.txt",
                new DatePeriod(2017, 5,1,2017,6,1));
        reportPngGenerator.generatePicFromTextFile("report.txt", "report.png");
        File file = new File("report.png");
        assertTrue(file.exists());
    }

}