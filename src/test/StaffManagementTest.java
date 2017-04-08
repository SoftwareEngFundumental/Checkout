package test;

import static org.junit.Assert.*;

import checkout.JsonDatabase;
import checkout.Staff;
import checkout.StaffManagement;
import checkout.StaffType;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.io.*;

import java.util.*;

@FixMethodOrder(MethodSorters.JVM)
public class StaffManagementTest
{
    private StaffManagement staffManagement = null;

    @Before
    public void setUp() throws Exception
    {
        staffManagement = new StaffManagement();
        staffManagement.createUser(StaffType.MANAGER, "Foo Delete", "f0o.bAr");
        staffManagement.createUser(StaffType.MANAGER, "Foo Password", "f0o.bAr");
        staffManagement.createUser(StaffType.MANAGER, "Foo Login", "f0o.bAr");
    }

    @After
    public void tearDown() throws Exception
    {
        // Do nothing?
    }


    @Test
    public void testUserLogin() throws Exception
    {
        assertTrue(staffManagement.userLogin("Foo Login", "f0o.bAr"));
        staffManagement.userLogout("Foo Login");
    }

    @Test
    public void testCreateUser() throws Exception
    {
        assertTrue(staffManagement.createUser(StaffType.MANAGER, "Foo Bar", "f0o.bAr"));
        ArrayList<Staff> staffList = staffManagement.getStaffList();
        assertTrue(staffList.size() > 0);
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        assertTrue(staffManagement.deleteUser("Foo Delete"));
    }


    @Test
    public void testChangeUserPassword() throws Exception
    {
        staffManagement.createUser(StaffType.SALES, "Foo Bar", "f0o.bAr");
        assertTrue(staffManagement.changeUserPassword("Foo Bar", "f0o.bAr", "fo0.baR"));
        staffManagement.deleteUser("Foo Bar");
    }

    @Test
    public void testUserLogout() throws Exception
    {
        staffManagement.userLogin("Foo Login", "f0o.bAr");
        assertTrue(staffManagement.userLogout("Foo Login"));
    }


    @Test
    public void testSaveUsersToFile() throws Exception
    {
        staffManagement.saveUsersToFile("settings.json");

        // Case #1, see if the file correctly created and saved to correct location.
        File testFile = new File("settings.json");
        assertTrue(testFile.exists());

        // Case #2, see if the file correctly saved with valid contents.
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Staff> testListObject = new ArrayList<>();

        testListObject = (ArrayList<Staff>)jsonDatabase.readObjectFromFile("settings.json", testListObject.getClass());

        assertTrue(testListObject.size() > 0);
    }

    @Test
    public void testLoadUsersFromFile() throws Exception
    {
        // Save to file and then load it, then see if it works.
        staffManagement.saveUsersToFile("settings.json");
        staffManagement.loadUsersFromFile("settings.json");

        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Staff> testListObject = new ArrayList<>();

        testListObject = (ArrayList<Staff>)jsonDatabase.readObjectFromFile("settings.json", testListObject.getClass());

        assertTrue(testListObject.size() > 0);
    }
}