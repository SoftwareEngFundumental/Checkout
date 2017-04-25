package report2png;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.*;

/**
 * Created by hu on 25/4/17.
 */
public class ReportPngGenerator
{
    public PicInfo GenerateImage(String strToGenerate, String fontName, int fontSize)
    {
        // Split multiple lines of strings to an ArrayList, in order to get the correct pic size.
        ArrayList<String> linesOfString = new ArrayList<>();
        linesOfString.addAll(Arrays.asList(strToGenerate.split("\n")));

        // Declare size of picture
        int picHeight = 0;
        int picWidth = 0;

        // Do a for loop to get the result...
        for(String currentLine : linesOfString)
        {
            // Temporarily set a buffered image first, in order to get the "graphical" string size
            BufferedImage bufferedImage = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);

            // Create a Graphic object and bind with the "Calibri" font
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setFont(new Font(fontName, Font.PLAIN, fontSize));

            // Create a font metric object
            FontMetrics fontMetrics = graphics2D.getFontMetrics();

            // Get the string height
            picHeight += fontMetrics.getHeight();

            // Find the longest line's width
            int currentLineWidth = fontMetrics.stringWidth(currentLine);

            if(picWidth < currentLineWidth)
            {
                picWidth = currentLineWidth;
            }

            // Save some memory lol
            graphics2D.dispose();
        }

        // Make it slightly larger in case something went wrong...
        picHeight += 50;
        picWidth += 50;

        return new PicInfo(picWidth, picHeight, linesOfString, fontName, fontSize);
    }

    public void GenerateImage(PicInfo picInfo, String pathOfPic)
    {
        // Split multiple lines of strings to an ArrayList, in order to get each lines' height
        ArrayList<String> linesOfString = picInfo.getLinesOfString();

        BufferedImage bufferedImage = new BufferedImage(
                picInfo.getPicWidth(),
                picInfo.getPicHeight(),
                BufferedImage.TYPE_INT_RGB);

        // Do the font metric stuff to calculate the string size and write string into image.
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setFont(new Font(picInfo.getFontName(), Font.PLAIN, picInfo.getFontSize()));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();

        // Current position of y-axis. For x-axis, it should be always zero (stay at the left side)
        int currentPosY = 0;

        // Do a for loop to get the current line of string
        for(String currentLine : linesOfString)
        {
            currentPosY += fontMetrics.getAscent();
            graphics2D.drawString(currentLine, 0, currentPosY);
        }

        // Dispose the Graphic2D object
        graphics2D.dispose();

        // Save picture to PNG file

        try
        {
            System.out.println("\nSaving sales report to path: " + pathOfPic);
            ImageIO.write(bufferedImage, "png", new File(pathOfPic));
        }
        catch (IOException error)
        {
            System.out.println("\nSales report generate failed.\nHere is the detailed information:\n\n");
            error.printStackTrace();
        }
    }
}
