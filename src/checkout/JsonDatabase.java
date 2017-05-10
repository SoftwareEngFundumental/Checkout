package checkout;

import checkout.Staff.ManagerStaff;
import checkout.Staff.SalesStaff;
import checkout.Staff.Staff;
import checkout.Staff.WarehouseStaff;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;


public class JsonDatabase
{
    public JsonDatabase()
    {

    }

    public void saveObjectToJsonFile(Object targetObject, String filePath)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(targetObject);
        saveToFile(jsonString, filePath);
    }

    public Object readObjectFromFile(String filePath, Class<?> classType)
    {
        Gson gson = new Gson();
        return gson.fromJson(readFromFile(filePath), classType);
    }

    public <T> T readObjectFromFile(String filePath, Type type)
    {
        Gson gson = new Gson();
        return gson.fromJson(readFromFile(filePath), type);
    }

    public ArrayList<Staff> readStaffListFromFile(String filePath)
    {
        // Create a gson object with specified converter
        Gson gsonWithConverter = new GsonBuilder().registerTypeAdapter(Staff.class, new StaffJsonDeserializer()).create();

        // Specify a staff type
        Type staffListType = new TypeToken<ArrayList<Staff>>(){}.getType();

        // Parse the damn JSON, then return
        return gsonWithConverter.fromJson(readFromFile(filePath), staffListType);

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

// Guys , remember to check Gson library references before editing this!!!
class StaffJsonDeserializer implements JsonDeserializer<Staff>
{
    @Override
    public Staff deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        // Get the type from JSON file first
        String type = json.getAsJsonObject().get("userType").getAsString();

        // Specify which sorts of class should be deserialized with...
        switch(type)
        {
            case "SALES":
                return context.deserialize(json, SalesStaff.class);

            case "WAREHOUSE":
                return context.deserialize(json, WarehouseStaff.class);

            case "MANAGER":
                return context.deserialize(json, ManagerStaff.class);

            default:
                throw new NullPointerException(String.format("What the hell is this: %s", type));
        }
    }
}
