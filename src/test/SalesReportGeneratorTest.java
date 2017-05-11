package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import checkout.SalesRecord.*;
import reportgen.report2png.*;
import reportgen.*;

import java.io.File;

import static org.junit.Assert.*;

public class SalesReportGeneratorTest
{
    SalesReportGenerator salesReportGenerator;

    @Before
    public void setUp() throws Exception
    {
        salesReportGenerator = new SalesReportGenerator();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void generateReportString() throws Exception
    {
        salesReportGenerator.generateReportString("Sales Record/", "report.txt");
        File file = new File("report.txt");
        assertTrue(file.exists());
    }

}