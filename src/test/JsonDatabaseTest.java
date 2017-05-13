package test;

import checkout.Staff.StaffType;
import org.junit.Before;
import org.junit.Test;
import checkout.util.JsonDatabase;
import checkout.Staff.Staff;
import checkout.Staff.StaffManagement;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;


public class JsonDatabaseTest
{
    @Before
    public void setUp() throws Exception
    {
        File file = new File("settings.json");
        if(file.exists())
        {
            if(file.delete())
            {
                System.out.println("[JsonDatabaseTest][DEBUG] Old setting file found and removed.");
            }
        }
    }

    @Test
    public void jsonFileOperation() throws Exception
    {
        // Create a StaffManagement object and a discrete staff list for reload test later
        StaffManagement staffManagement = new StaffManagement();
        ArrayList<Staff> reloadedStaffList;

        // Create a user
        staffManagement.createUser(StaffType.MANAGER, "Foo Bar", "fo0.BaR");

        // Write it to JSON
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(staffManagement.getStaffList(), "settings.json");

        // Scenario #1, check if file saved to correct location or not
        File testFile = new File("settings.json");
        assertTrue(testFile.exists());

        // Scenario #2, load the file again and see if it has valid content (same length)
        reloadedStaffList = jsonDatabase.readStaffListFromFile("settings.json");
        assertTrue(reloadedStaffList.size() == staffManagement.getStaffList().size());

        // Scenario #3, evaluate if this user object value is correctly saved in the list
        assertTrue(reloadedStaffList.get(0).getUserName().equals("Foo Bar"));
        assertTrue(reloadedStaffList.get(0).getUserPassword().equals("fo0.BaR"));
        assertTrue(reloadedStaffList.get(0).getUserType().equals(StaffType.MANAGER));
        assertTrue(reloadedStaffList.get(0).getUserId() == staffManagement.getStaffList().get(0).getUserId());
    }


}