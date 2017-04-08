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

public class StaffManagementTest
{
    private StaffManagement staffManagement = null;

    @Before
    public void setUp() throws Exception
    {
        staffManagement = new StaffManagement();

        // Create three users for testing. Here we can't just use one user,
        // it may conflict with other test methods which will cause unexpected results.
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
        // Scenario #1: Correct password, should return true
        assertTrue(staffManagement.userLogin("Foo Login", "f0o.bAr"));

        // Scenario #2: Incorrect password, should return false
        assertTrue(!staffManagement.userLogin("Foo Login", "who_the_hell_knows"));

        // Clean up for future tests
        staffManagement.userLogout("Foo Login");
    }

    @Test
    public void testCreateUser() throws Exception
    {
        // Scenario #1: Create a user that does not exist in the list
        assertTrue(staffManagement.createUser(StaffType.MANAGER, "Foo Bar", "f0o.bAr"));

        // Scenario #2: Create a user that ALREADY EXISTS in the list
        assertTrue(!staffManagement.createUser(StaffType.MANAGER, "Foo Bar", "rand0m"));

        // Scenario #3: Find out the use from the list, should not be null
        assertTrue(staffManagement.findUserFromList("Foo Bar") != null);
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        // Scenario #1: Delete this user from the list
        assertTrue(staffManagement.deleteUser("Foo Delete"));

        // Scenario #2: Delete a user that DOES NOT exist in the list
        assertTrue(!staffManagement.deleteUser("Foo Oops"));

        // Scenario #3: Check if the "Foo Delete" has been correctly deleted
        assertTrue(staffManagement.findUserFromList("Foo Delete") != null);
    }


    @Test
    public void testChangeUserPassword() throws Exception
    {
        // Create a new user for testing
        staffManagement.createUser(StaffType.SALES, "Foo Bar", "f0o.bAr");

        // Scenario #1: Check if can set a new password
        assertTrue(staffManagement.changeUserPassword("Foo Bar", "f0o.bAr", "fo0.baR"));

        // Scenario #2: If old password is not correct, check if it returns false result
        assertTrue(!staffManagement.changeUserPassword("Foo Bar", "O0ps!", "fo0.baR"));

        // Clean it up
        staffManagement.deleteUser("Foo Bar");
    }

    @Test
    public void testUserLogout() throws Exception
    {

        staffManagement.userLogin("Foo Login", "f0o.bAr");

        // Scenario #1: Try to logout, return true if loginStatus is correct.
        assertTrue(staffManagement.userLogout("Foo Login"));

        // Scenario #2: If the user has logged out, then it should return false.
        assertTrue(!staffManagement.userLogout("Foo Login"));

        // Scenario #3: If the user does not exist, then should also return false.
        assertTrue(!staffManagement.userLogout("Foo Null"));
    }


    @Test
    public void testSaveUsersToFile() throws Exception
    {
        staffManagement.saveUsersToFile("settings.json");

        // Scenario #1, see if the file correctly created and saved to correct location.
        File testFile = new File("settings.json");
        assertTrue(testFile.exists());

        // Scenario #2, see if the file correctly saved with valid contents.
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Staff> testListObject = new ArrayList<>();

        testListObject = jsonDatabase.readStaffListFromFile("settings.json");
        assertTrue(testListObject.size() > 0);
    }

    @Test
    public void testLoadUsersFromFile() throws Exception
    {
        // Create a new staff for testing
        staffManagement.createUser(StaffType.WAREHOUSE, "Foo Bar Save", "f0o.bAr");

        // Save to file and then load it, then see if it works.
        staffManagement.saveUsersToFile("settings.json");

        // Load staffs from file
        staffManagement.loadUsersFromFile("settings.json");

        // Scenario #1: Try do a login
        assertTrue(staffManagement.userLogin("Foo Bar Save", "f0o.bAr"));

        // Scenario #2: ry do a logout
        assertTrue(staffManagement.userLogout("Foo Bar Save"));
    }
}