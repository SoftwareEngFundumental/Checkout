package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import report2png.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by hu on 25/4/17.
 */
public class ReportPngGeneratorTest
{
    private ReportPngGenerator reportPngGenerator = null;
    private String testStr = "";

    @Before
    public void prepareToTest() throws Exception
    {
        reportPngGenerator = new ReportPngGenerator();

        // Cited from here: "https://en.wikipedia.org/wiki/Waltzing_Matilda"
        // ...this is the first Australian song I've learned when I was in primary school lol...
        testStr =

            "Once a jolly swagman camped by a billabong\n" +
            "Under the shade of a coolibah tree,\n" +
            "And he sang as he watched and waited till his billy boiled:\n" +
            "\"Who'll come a-waltzing Matilda, with me?\"\n" +
            "\n" +
            "Waltzing Matilda, waltzing Matilda\n" +
            "You'll come a-waltzing Matilda, with me\n" +
            "And he sang as he watched and waited till his billy boiled:\n" +
            "\"You'll come a-waltzing Matilda, with me.\"\n" +
            "\n" +
            "Down came a jumbuck to drink at that billabong.\n" +
            "Up jumped the swagman and grabbed him with glee.\n" +
            "And he sang as he shoved that jumbuck in his tucker bag:\n" +
            "\"You'll come a-waltzing Matilda, with me.\"\n" +
            "\n" +
            "Up rode the squatter, mounted on his thoroughbred.\n" +
            "Down came the troopers, one, two, and three.\n" +
            "\"Whose is that jumbuck you've got in your tucker bag?\n" +
            "You'll come a-waltzing Matilda, with me.\"\n" +
            "\n" +
            "Up jumped the swagman and sprang into the billabong.\n" +
            "\"You'll never take me alive!\" said he\n" +
            "And his ghost may be heard as you pass by that billabong:\n" +
            "\"Who'll come a-waltzing Matilda, with me?\"";
    }

    @Test
    public void getImageSize() throws Exception
    {
        PicInfo picInfo = reportPngGenerator.getImageSize(testStr, "Calibri", 15);
        assertNotNull(picInfo);

        // Actually I don't know the size of this picture will be (on various platforms),
        // so just leave some numbers there and it should works.
        assertTrue(picInfo.getPicHeight() > 200);
        assertTrue(picInfo.getPicWidth() > 200);
    }

    @Test
    public void generateImage() throws Exception
    {
        reportPngGenerator.generateImage(
                reportPngGenerator.getImageSize(testStr, "Calibri", 15),
                "test.png");

        // Don't know how to re-parse (Do an OCR???) pictures in Java lol,
        // so just judge if this file is generated or not...
        File file = new File("test.png");
        assertTrue(file.exists());
    }

}