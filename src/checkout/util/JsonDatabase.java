package checkout.util;

import checkout.SalesRecord.SalesRecordLine;
import checkout.Staff.ManagerStaff;
import checkout.Staff.SalesStaff;
import checkout.Staff.Staff;
import checkout.Staff.WarehouseStaff;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;


public class JsonDatabase
{
    public void saveObjectToJsonFile(Object targetObject, String filePath)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(targetObject);
        TextFile.writeStringToFile(jsonString, filePath);
    }

    /**
     * Read a JSON file and set a type to it,
     * then return an object with this type and alongside with the JSON content.
     */
    public <T> T readObjectFromFile(String filePath, Type type)
    {
        Gson gson = new Gson();
        return gson.fromJson(TextFile.readStringFromFile(filePath), type);
    }

    /**
     * Since Staff is an abstract class, here we need to parse each kind of staff separately.
     */
    public ArrayList<Staff> readStaffListFromFile(String filePath)
    {
        // Create a gson object with specified converter
        Gson gsonWithConverter = new GsonBuilder().registerTypeAdapter(Staff.class, new StaffJsonDeserializer()).create();

        // Specify a staff type
        Type staffListType = new TypeToken<ArrayList<Staff>>(){}.getType();

        // Parse the damn JSON, then return
        return gsonWithConverter.fromJson(TextFile.readStringFromFile(filePath), staffListType);

    }
}

/**
 * Gson JsonDeserializer for Staff abstract class
 *
 * See here for more details: https://static.javadoc.io/com.google.code.gson/gson/2.6.2/com/google/gson/JsonDeserializer.html
 *                            https://stackoverflow.com/questions/6096940/how-do-i-write-a-custom-json-deserializer-for-gson
 *
 */
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
