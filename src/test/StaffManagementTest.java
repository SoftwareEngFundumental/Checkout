package test;

import static org.junit.Assert.*;

import checkout.JsonDatabase;
import checkout.Staff;
import checkout.UserManagement;
import checkout.UserType;
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
    private UserManagement userManagement = null;

    @Before
    public void setUp() throws Exception
    {
        userManagement = new UserManagement();
        userManagement.createUser(UserType.MANAGER, "Foo Delete", "f0o.bAr");
        userManagement.createUser(UserType.MANAGER, "Foo Password", "f0o.bAr");
        userManagement.createUser(UserType.MANAGER, "Foo Login", "f0o.bAr");
    }

    @After
    public void tearDown() throws Exception
    {
        // Do nothing?
    }


    @Test
    public void testUserLogin() throws Exception
    {
        assertTrue(userManagement.userLogin("Foo Login", "f0o.bAr"));
        userManagement.userLogout("Foo Login");
    }

    @Test
    public void testCreateUser() throws Exception
    {
        assertTrue(userManagement.createUser(UserType.MANAGER, "Foo Bar", "f0o.bAr"));
        ArrayList<Staff> staffList = userManagement.getStaffList();
        assertTrue(staffList.size() > 0);
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        assertTrue(userManagement.deleteUser("Foo Delete"));
    }


    @Test
    public void testChangeUserPassword() throws Exception
    {
        userManagement.createUser(UserType.SALES, "Foo Bar", "f0o.bAr");
        assertTrue(userManagement.changeUserPassword("Foo Bar", "f0o.bAr", "fo0.baR"));
        userManagement.deleteUser("Foo Bar");
    }

    @Test
    public void testUserLogout() throws Exception
    {
        userManagement.userLogin("Foo Login", "f0o.bAr");
        assertTrue(userManagement.userLogout("Foo Login"));
    }


    @Test
    public void testSaveUsersToFile() throws Exception
    {
        userManagement.saveUsersToFile("settings.json");

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
        userManagement.saveUsersToFile("settings.json");
        userManagement.loadUsersFromFile("settings.json");

        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Staff> testListObject = new ArrayList<>();

        testListObject = (ArrayList<Staff>)jsonDatabase.readObjectFromFile("settings.json", testListObject.getClass());

        assertTrue(testListObject.size() > 0);
    }
}