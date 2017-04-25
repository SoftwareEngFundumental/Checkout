package report2png;

import java.util.*;

/**
 * Created by hu on 25/4/17.
 */
public class PicInfo
{
    public PicInfo(int width, int height, ArrayList<String> linesOfString, String fontName, int fontSize)
    {
        this.picHeight          = height;
        this.picWidth           = width;
        this.linesOfString      = linesOfString;
        this.fontName           = fontName;
        this.fontSize           = fontSize;
    }

    private     int                 picWidth;
    private     int                 picHeight;
    private     String              fontName;
    private     int                 fontSize;
    private     ArrayList<String>   linesOfString;
    public      int getPicWidth()                       { return picWidth;      }
    public      int getPicHeight()                      { return picHeight;     }
    public      ArrayList<String> getLinesOfString()    { return linesOfString; }
    public      String getFontName()                    { return fontName;      }
    public      int getFontSize()                       { return fontSize;      }
}
