import com.google.gson.Gson;
import java.io.*;
import java.util.*;


public class ObjectToJson
{
    public ObjectToJson()
    {

    }

    public void saveObjectToJsonFile(Object targetObject, String filePath)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(targetObject);
        saveToFile(jsonString, filePath);
    }

    public Object readObjectFromFile(String filePath, Class<?> type)
    {
        Gson gson = new Gson();
        return gson.fromJson(readFromFile(filePath), type);
    }

    private void saveToFile(String jsonString, String filePath)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write(jsonString);
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println(String.format("[Error] IOException thrown when saving file %s", filePath));

        }
    }

    private String readFromFile(String filePath)
    {
        String jsonString = "";

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            jsonString += reader.readLine();

        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println(String.format("[Error] File not found at %s", filePath));
        }
        catch (IOException ioException)
        {
            System.out.println(String.format("[Error] IOException thrown when saving file %s", filePath));
        }

        return jsonString;
    }
}
