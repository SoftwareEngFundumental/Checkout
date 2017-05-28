package checkout.util;

import java.io.*;
import java.util.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */

public class TextFile
{
    public static void writeStringToFile(String jsonString, String filePath)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write(jsonString);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println(String.format("[Error] IOException thrown when saving file %s", filePath));
        }
    }

    public static String readStringFromFile(String filePath)
    {
        StringBuilder jsonString = new StringBuilder();
        String strBuffer;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while((strBuffer = reader.readLine()) != null)
            {
                jsonString.append(strBuffer);
                jsonString.append('\n');
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println(String.format("[Error] File not found at %s", filePath));
        }
        catch (IOException ioException)
        {
            System.out.println(String.format("[Error] IOException thrown when saving file %s", filePath));
        }

        return jsonString.toString();
    }
}
