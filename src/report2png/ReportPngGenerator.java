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
    public PicInfo getImageSize(String strToGenerate, String fontName, int fontSize, int horizonEdgeScale)
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

            // Create a Graphic object and bind with the font which user specified.
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setFont(new Font(fontName, Font.PLAIN, fontSize));

            // Create a font metric object
            FontMetrics fontMetrics = graphics2D.getFontMetrics();

            // Get the string height
            picHeight += fontMetrics.getHeight();

            // Find the longest line's width
            int currentLineWidth = fontMetrics.stringWidth(currentLine);

            // Get the longest line width as the picture width dynamically,
            // to prevent something to be covered or too much blank space at the edge.
            if(picWidth < currentLineWidth)
            {
                picWidth = currentLineWidth;
            }

            // Save some memory lol
            graphics2D.dispose();
        }

        // Make the width slightly larger
        // So here we use a denominator value to prevent the picture's edge is too narrow,
        // otherwise the last char of every line will become hard to read.
        if(horizonEdgeScale > 1)
        {
            picWidth = picWidth + (picWidth / (horizonEdgeScale * 2)) * 3;
        }
        else
        {
            picWidth = picWidth + (picWidth / 10) * 3;
        }


        return new PicInfo(picWidth, picHeight, linesOfString, fontName, fontSize, horizonEdgeScale);
    }

    public void generateImage(PicInfo picInfo, String pathOfPic)
    {
        // Split multiple lines of strings to an ArrayList, in order to get each lines' height
        ArrayList<String> linesOfString = picInfo.getLinesOfString();

        BufferedImage bufferedImage = new BufferedImage(
                picInfo.getPicWidth(),
                picInfo.getPicHeight(),
                BufferedImage.TYPE_INT_RGB);

        // Do the font metric stuff to calculate the string size and write string into image.
        Graphics2D graphics2D = bufferedImage.createGraphics();

        // Set some parameters before rendering, otherwise the picture will be very ugly.
        // For these references, see here: https://docs.oracle.com/javase/tutorial/2d/advanced/quality.html
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);


        // Set font and create font metric
        graphics2D.setFont(new Font(picInfo.getFontName(), Font.PLAIN, picInfo.getFontSize()));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();

        // Current position of y-axis.
        // This value must not be zero, otherwise it will become very ugly.
        int currentPosY = 25;

        // For x-axis, it should be always be the same (left-aligned), but not 0,
        // otherwise the first char of every new line will become hard to read.
        int posX;
        if(picInfo.getHorizonEdgeScale() > 1)
        {
            posX = picInfo.getPicWidth() / (picInfo.getHorizonEdgeScale() * 2);
        }
        else
        {
            posX = 25;
        }

        // Do a for loop to get the current line of string
        for(String currentLine : linesOfString)
        {
            currentPosY += fontMetrics.getAscent();
            graphics2D.drawString(currentLine, posX, currentPosY);
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
