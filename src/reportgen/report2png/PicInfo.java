package reportgen.report2png;

import java.util.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 *
 * PicInfo holds some attributes about the picture which needs to be generated later.
 *
 * It inculdes: Picture size (quality related)
 *              Picture font size (quality related, similar as DPI settings in Windows 10)
 *              Picture font (font name in String)
 *              Lines of strings (this is required due to the limitation of Java libraries. It has to write line by line anyway)
 *              Edge scale (how many space should be reserved in the edges of the picture.)
 *
 */
public class PicInfo
{
    public PicInfo(int width, int height, ArrayList<String> linesOfString, String fontName, int fontSize, int horizonEdgeScale)
    {
        this.picHeight          = height;
        this.picWidth           = width;
        this.linesOfString      = linesOfString;
        this.fontName           = fontName;
        this.fontSize           = fontSize;
        this.horizonEdgeScale   = horizonEdgeScale;
    }

    private     int                 picWidth;
    private     int                 picHeight;
    private     String              fontName;
    private     int                 fontSize;
    private     ArrayList<String>   linesOfString;
    private     int                 horizonEdgeScale;
    public      int getPicWidth()                       { return picWidth;      }
    public      int getPicHeight()                      { return picHeight;     }
    public      ArrayList<String> getLinesOfString()    { return linesOfString; }
    public      String getFontName()                    { return fontName;      }
    public      int getFontSize()                       { return fontSize;      }
    public      int getHorizonEdgeScale()               { return horizonEdgeScale; }
}
