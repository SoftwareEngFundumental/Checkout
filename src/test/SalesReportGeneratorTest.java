package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;
import checkout.SalesRecord.*;
import reportgen.report2png.*;
import reportgen.*;

import java.io.File;

import static org.junit.Assert.*;


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
        salesReportGenerator.generateReportString("Sales Record/", "report.txt");
        File file = new File("report.txt");
        assertTrue(file.exists());
    }

    @Test
    public void generateReportPic() throws Exception
    {
        salesReportGenerator.generateReportString("Sales Record/", "report.txt");
        reportPngGenerator.generatePicFromTextFile("report.txt", "report.png");
        File file = new File("report.png");
        assertTrue(file.exists());
        assertTrue(file.getTotalSpace() > 1);
    }

}